package com.study.sys_power.oshi;


import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import com.study.sys_power.oshi.model.*;
import com.study.sys_power.util.IpInfoUtils;
import lombok.Data;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;
import oshi.SystemInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 服务器相关信息
 */
@Data
public class SystemHardwareInfo {

    private static final int OSHI_WAIT_SECOND = 1000;

    /**
     * CPU相关信息
     */
    private CpuInfo cpu = new CpuInfo();

    /**
     * 内存相关信息
     */
    private MemInfo mem = new MemInfo();

    /**
     * JVM相关信息
     */
    private JvmInfo jvm = new JvmInfo();

    /**
     * 系统相关信息
     */
    private SysInfo sys = new SysInfo();

    /**
     * 文件系统相关
     */
    private List<SysFileInfo> sysFiles = new LinkedList<>();

    public void copyTo(){
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        setCpuInfo(hal.getProcessor());
        setMemInfo(hal.getMemory());
        setSysInfo();
        setJvmInfo();
        setSysFilesInfo(si.getOperatingSystem());
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor){
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user+nice+cSys+idle+iowait+irq+softirq+steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);

    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memInfo){
        mem.setTotal(memInfo.getTotal());
        mem.setUsed(memInfo.getTotal()-memInfo.getAvailable());
        mem.setFree(memInfo.getAvailable());
    }

    /**
     * 设置系统信息
     */
    private void setSysInfo(){
        Properties props = System.getProperties();
        sys.setComputerName(IpInfoUtils.getHostName());
        sys.setComputerIp(NetUtil.getLocalhostStr());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置JVM信息
     */
    private void setJvmInfo(){
        Properties props = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置文件系统相关信息
     */
    private void setSysFilesInfo(OperatingSystem os){
        FileSystem fileSystem = os.getFileSystem();
        OSFileStore[] fsArray = fileSystem.getFileStores();
        for(OSFileStore fs : fsArray){
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            SysFileInfo sysFileInfo = new SysFileInfo();
            sysFileInfo.setDirName(fs.getMount());
            sysFileInfo.setSysTypeName(fs.getType());
            sysFileInfo.setTypeName(fs.getName());
            sysFileInfo.setTotal(convertFileSize(total));
            sysFileInfo.setFree(convertFileSize(free));
            sysFileInfo.setUsed(convertFileSize(used));

            if(total == 0){
                sysFileInfo.setUsage(0);
            }else{
                sysFileInfo.setUsage(NumberUtil.mul(NumberUtil.div(used,total,4),100));
            }
            sysFiles.add(sysFileInfo);
        }
    }

    public String convertFileSize(long size){
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if(size >= gb){
            return String.format("%.1f GB", (float) size / gb);
        }else if(size >= mb){
            float f = (float) size / mb;
            return String.format(f>100? "%.0f MB" : "%.1f MB",f);
        }else if(size>=kb){
            float f = (float) size / kb;
            return String.format(f>100?"%.f KB":"%.1f KB", f);
        }else{
            return String.format("%d B",size);
        }
    }
}
