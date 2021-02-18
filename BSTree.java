// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }
    private BSTree FindRoot(){
        BSTree a=this;
        while(a.parent!=null){
            a=a.parent;
        }
        return a.right;
    }
    public BSTree Insert(int address, int size, int key) 
    {
        BSTree a = this.FindRoot();
        if(a!=null){
        BSTree b= new BSTree(address,size,key);
        while(true){
            if(key<=a.key){
                if
                (a.left==null) {
                    a.left=b;
                    b.parent=a;
                    return b;
                }
                else{
                    a=a.left;
                }
               
            }
            else
            {
                if(a.right==null){
                    a.right=b;
                    b.parent=a;
                    return b;
                }
                else{
                    a=a.right;
                }
            }
        }
    }
        else{
            BSTree b= new BSTree(address,size,key);
            this.right=b;
            b.parent=this;
            return b;
        }
    }
    private BSTree successor(){
        BSTree a = this;
        if(a.right!=null){
            a=a.right;
        }
        while(a.left!=null){
            a=a.left;
        }
        return a;
    }

    public boolean Delete(Dictionary e)
    { 
        BSTree a = this;
            if(this.parent==null){
                if(this.right!=null){
                    a=this.right;
                }
                else{
                    return false;
                }
            }
        while(true){
            if(a.key==e.key && a.size==e.size && a.address==e.address){
                if(a.left==null && a.right==null){
                    if(a.parent.left==a){
                        a.parent.left=null;
                        return true;
                    }
                    else{
                        a.parent.right=null;
                        return true;
                    }
                }
                else if(a.left!=null&&a.right!=null){
                    BSTree b = a.successor();
                    a.key=b.key;
                    a.size=b.size;
                    a.address=b.address;
                    if(b.left==null && b.right==null){
                    if(b.parent.left==b){
                        b.parent.left=null;
                        return true;
                    }
                    else{
                        b.parent.right=null;
                        return true;
                    }
                }
                    else{
                    if(b.parent.left==b){
                        if(b.left!=null){
                            b.parent.left=b.left;
                            b.left.parent=b.parent;
                            return true;
                        }
                        else{
                            b.parent.left=b.right;
                            b.right.parent=b.parent;
                            return true;
                        }
                    }
                    else{
                        if(b.left!=null){
                            b.parent.right=b.left;
                            b.left.parent=b.parent;
                            return true;
                        }
                        else{
                            b.parent.right=b.right;
                            b.right.parent=b.parent;
                            return true;
                        }
                    }
                }

                    }
                else{
                    if(a.parent.left==a){
                        if(a.left!=null){
                            a.parent.left=a.left;
                            a.left.parent=a.parent;
                            return true;
                        }
                        else{
                            a.parent.left=a.right;
                            a.right.parent=a.parent;
                            return true;
                        }
                    }
                    else{
                        if(a.left!=null){
                            a.parent.right=a.left;
                            a.left.parent=a.parent;
                            return true;
                        }
                        else{
                            a.parent.right=a.right;
                            a.right.parent=a.parent;
                            return true;
                        }
                    }
                }
            }
            else if(e.key<=a.key){
                if(a.left==null){
                    return false;
                }
                a=a.left;
            }
            else{
                if(a.right==null){
                    return false;
                }
                a=a.right;
            }
        }
    }
        
    public BSTree Find(int key, boolean exact)
    {
        if(exact==true){
            BSTree a = this;
            if(this.parent==null){
                if(this.right!=null){
                                    a=this.right;
                }
                else{
                    return null;
                }
            }
            while(true){
                if(a.key==key){
                    return a;
                }
                else if (key<a.key){
                    if(a.left==null){
                        return null;
                    }
                    else{
                        a=a.left;            
                    }
                }
                else{
                    if(a.right==null){
                        return null;
                    }
                    else{
                        a=a.right;
                    }
                }

            }
        } 
        else{
            BSTree a = this;
            if(this.parent==null){
                if(this.right!=null){
                    a=this.right;
                }
                else{
                    return null;
                }
                
            }
            a=a.getFirst();
            while(a!=null && a.key<key){
                a=a.getNext();
                }
            if(a!=null){
                return a;
            }
            else{
                return null;
            }
            }
        }
    private boolean isEmpty(){
        if(this==null && this.left==null && this.right==null){
            return true;
        }
        else{
            return false;
        }
    }

    public BSTree getFirst()
    {
        if(!this.isEmpty()){
            BSTree a = this;
            if(this.parent==null){
                if(this.right!=null){
                     a=this.right;
                }
                else{
                    return null;
                }
               
            }
            while(a.left!=null){
                a=a.left;
            }
            return a;
        }
                

        else{
                return null;
            }
            
    }

    public BSTree getNext()
    { 
        if(!this.isEmpty()){
            BSTree a = this;
            if(this.parent==null){
                if(this.right!=null){
                     a=this.right;
                }
                else{
                    return null;
                }
            }
            if(a.right!=null){
                return a.right.getFirst();
            }
            else{
                while(a.parent!=null && a.parent.left!=a){
                    a=a.parent;
                }
                if(a.parent==null){
                    return null;
                }
                else{
                    return a.parent;
                }                
            }
        }
        else{
            return null;
        }
    }
    //private boolean sanitySentinel(){
    //    BSTree a = this.FindRoot();
    //    if(a.parent!=null || a.left!=null){
    //        return false;
    //    }
    //    else{
    //        return true;
    //    }
    //}
    private boolean sanitycheck(BSTree a)
    {
        if(a==null){
            return true;
        }
        if((a.left!=null && a.left.key>a.key)||(a.right!=null && a.right.key<=a.key)){
            return false;
        }
        else{
            return (sanitycheck(a.left) && sanitycheck(a.right));
        }
    }
    public boolean sanity()
    { 
        BSTree a = this;
            if(this.parent==null){
                if(this.right!=null){
                    a=this.right;
                }
                else{
                    return false;
                }
            }
        return sanitycheck(a);
    }

}


 


