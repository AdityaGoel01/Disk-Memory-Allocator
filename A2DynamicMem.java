// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
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
            freeBlk.Insert(node.address,node.size,node.size);
            allocBlk.Delete(node);
            return 0;
        }   
        else{
            return -1;
        }     
    }
    public void Defragment() {
        if(freeBlk.getFirst()!=null){
        if(type==2){
            BSTree a = new BSTree();
            BSTree a1= new BSTree();
            Dictionary b= freeBlk.getFirst();

        while(b!=null){
            a.Insert(b.address,b.size,b.address);
            b=b.getNext();
        }
        b=a.getFirst();
        while(b.getNext()!=null){
            if(b.address+b.size==b.getNext().address){
                b.size=b.size+b.getNext().size;
                a.Delete(b.getNext());
            }
            else{
                b=b.getNext();
            }
        }
        b= a.getFirst();
        while(b!=null){
            a1.Insert(b.address,b.size,b.size);
            b=b.getNext();
        }
            freeBlk=a1;
        }
        else if (type == 3){
            AVLTree a = new AVLTree();
            AVLTree a1= new AVLTree();
            Dictionary b= freeBlk.getFirst();

        while(b!=null){
            a.Insert(b.address,b.size,b.address);
            b=b.getNext();
        }
        b=a.getFirst();
        while(b.getNext()!=null){
            if(b.address+b.size==b.getNext().address){
                b.size=b.size+b.getNext().size;
                a.Delete(b.getNext());
            }
            else{
                b=b.getNext();
            }
        }
        b= a.getFirst();
        while(b!=null){
            a1.Insert(b.address,b.size,b.size);
            b=b.getNext();
        }
            freeBlk=a1;
        }
        else{
            return;
        }
    }
    else{
        return;
    }
}
public static void main(String[] args) {
    A2DynamicMem a =new A2DynamicMem(10000,3);
    a.Allocate(20);
    System.out.println(a.allocBlk.getFirst().address);
    System.out.println(a.freeBlk.getFirst().address);
    a.Allocate(40);
    System.out.println(a.allocBlk.getFirst().address);
    System.out.println(a.allocBlk.getFirst().getNext().address);
    System.out.println(a.freeBlk.getFirst().address);
    a.Allocate(60);
    System.out.println(a.allocBlk.getFirst().address);
    System.out.println(a.allocBlk.getFirst().getNext().address);
    System.out.println(a.allocBlk.getFirst().getNext().getNext().address);
    System.out.println(a.freeBlk.getFirst().address);
    a.Free(20);
    System.out.println(a.allocBlk.getFirst().address);
    System.out.println(a.allocBlk.getFirst().getNext().address);
    System.out.println(a.freeBlk.getFirst().address);
    System.out.println(a.freeBlk.getFirst().getNext().address);
}
}