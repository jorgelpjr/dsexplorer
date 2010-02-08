package luz.dsexplorer.winapi.api;

import java.awt.image.BufferedImage;
import java.util.List;

import luz.dsexplorer.winapi.constants.GAFlags;
import luz.dsexplorer.winapi.constants.ProcessInformationClass;
import luz.dsexplorer.winapi.jna.Kernel32.MEMORY_BASIC_INFORMATION;
import luz.dsexplorer.winapi.jna.Ntdll.PROCESS_BASIC_INFORMATION;
import luz.dsexplorer.winapi.jna.Psapi.LPMODULEINFO;
import luz.dsexplorer.winapi.jna.Psapi.PPROCESS_MEMORY_COUNTERS;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface WinAPI {
	
	public ProcessList getProcessList();
	public Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, int dwProcessId) throws Exception;
	public MEMORY_BASIC_INFORMATION VirtualQueryEx(Pointer hProcess,Pointer lpAddress);
	public void ReadProcessMemory(Pointer hProcess, Pointer pointer, Pointer outputBuffer, int nSize, IntByReference outNumberOfBytesRead) throws Exception;
	public String GetModuleFileNameExA(Pointer hProcess,Pointer hModule);
	public LPMODULEINFO GetModuleInformation(Pointer hProcess, Pointer hModule) throws Exception;
	public String GetProcessImageFileNameA(Pointer hProcess);
	public List<Pointer> EnumProcessModules(Pointer hProcess) throws Exception;
	public PPROCESS_MEMORY_COUNTERS GetProcessMemoryInfo(Pointer Process) throws Exception;
	public Pointer ExtractSmallIcon(String lpszFile, int nIconIndex);
	public PROCESS_BASIC_INFORMATION NtQueryInformationProcess(Pointer ProcessHandle, ProcessInformationClass pic);
	public Pointer getHIcon(Pointer hWnd);
	public BufferedImage getIcon(Pointer hIcon);
    public Pointer GetAncestor(Pointer hwnd, GAFlags gaFlags);


}
