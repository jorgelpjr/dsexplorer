package luz.dsexplorer.winapi.api;

import javax.swing.tree.MutableTreeNode;


public interface ResultList extends MutableTreeNode {

	public Process getProcess();
    public void add(MutableTreeNode newChild);

}