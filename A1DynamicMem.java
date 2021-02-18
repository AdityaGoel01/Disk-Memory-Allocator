// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        Dictionary node=freeBlk.Find(blockSize,false);
        if(node!=null){
            allocBlk.Insert(node.address,blockSize,node.address);
            freeBlk.Delete(node);
            if(node.size==blockSize){
                return node.address;
            }
            else{
                freeBlk.Insert(node.address+blockSize,node.size-blockSize,node.size-blockSize);
                return node.address;
            }
        }
        else{
            return -1;            
        }

    } 
    
    public int Free(int startAddr) {
        Dictionary node= allocBlk.Find(startAddr,true);
        if(node!=null){
            allocBlk.Delete(node);
            freeBlk.Insert(node.address,node.size,node.size);
            return 0;
        }   
        else{
            return -1;
        }     
    }
    
}