package luz.eveMonitor.datastructure;

import java.util.Iterator;

import luz.dsexplorer.winapi.api.Process;

import com.sun.jna.Pointer;

public class PyDict extends PyObject {

	public PyDict(PyObject_VAR_HEAD head, Process process) {
		super(head, 20, process);
	}
	
	public int		getMa_Fill    (){return super.getInt   (0);}	//24
	public int		getMa_Used    (){return super.getInt   (4);}
	public int		getMa_Mask    (){return super.getInt   (8);}
	public int		getMa_Table   (){return super.getInt   (12);}
	public int		getMa_Lokup   (){return super.getInt   (16);}
	public PyDictEntry	getDictEntry(int i){
		PyDictEntry entry=new PyDictEntry(process);
		try {
			process.ReadProcessMemory(Pointer.createConstant(getMa_Table()+(4*3)*i), entry, (int)entry.getSize(), null);
			return entry;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Iterator<PyDictEntry> getDictEntries(){
		return new Iterator<PyDictEntry>() {
			int index=0;
			int limit=getMa_Mask()+1;

			@Override
			public boolean hasNext() {
				return index<limit;
			}

			@Override
			public PyDictEntry next() {
				return getDictEntry(index++);
			}

			@Override
			public void remove() {}
		};
	}

}