// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private AVLTree FindRoot(){
        AVLTree a=this;
        while(a.parent!=null){
            a=a.parent;
        }
        return a.right;
    }
    private int height(AVLTree node){
        if(node==null){
            return 0;
        }
        else{
            return node.height;
        }

    }
    private void updateHeight(AVLTree node){
        if(node==null){
            return;
        }
        else{
            updateHeight(node.left);
            updateHeight(node.right);
            node.height=Math.max(height(node.left),height(node.right))+1;
        }
    }
    private int balance(AVLTree node){
        if(node==null){
            return 0;
        }
        else{
            return height(node.left)-height(node.right);
        }
    }
    private void checkBalance(AVLTree x){
        AVLTree current1=x;
        while(true){
        AVLTree current2=current1.parent;
        if(current2.parent.parent==null){
            return;
        }
        else{
            AVLTree current3=current2.parent;
            if(balance(current3)>1){
                if(current2.left==current1){
                    current3.left=current2.right;
                    if(current2.right!=null){
                        current2.right.parent=current3;
                    }
                    current2.right=current3;
                    if(current3.parent.left==current3){
                        current3.parent.left=current2;
                    }
                    else{
                        current3.parent.right=current2;
                    }
                    current2.parent=current3.parent;
                    current3.parent=current2;
                }
                else{
                    current3.left=current1.right;
                    if(current1.right!=null){
                        current1.right.parent=current3;
                    }
                    current1.right=current3;
                    if(current3.parent.left==current3){
                        current3.parent.left=current1;
                    }
                    else{
                        current3.parent.right=current1;
                    }
                    current1.parent=current3.parent;
                    current3.parent=current1;
                    current2.right=current1.left;
                    if(current1.left!=null){
                        current1.left.parent=current2;
                    }
                    current1.left=current2;
                    current2.parent=current1;
                }
                updateHeight(this.FindRoot());
                return;
            }
            if(balance(current3)<-1){
                if(current2.right==current1){
                    current3.right=current2.left;
                    if(current2.left!=null){
                        current2.left.parent=current3;
                    }
                    current2.left=current3;
                    if(current3.parent.right==current3){
                        current3.parent.right=current2;
                    }
                    else{
                        current3.parent.left=current2;
                    }
                    current2.parent=current3.parent;
                    current3.parent=current2;
                }
                else{
                    current3.right=current1.left;
                    if(current1.left!=null){
                        current1.left.parent=current3;
                    }
                    current1.left=current3;
                    if(current3.parent.right==current3){
                        current3.parent.right=current1;
                    }
                    else{
                        current3.parent.left=current1;
                    }
                    current1.parent=current3.parent;
                    current3.parent=current1;
                    current2.left=current1.right;
                    if(current1.right!=null){
                        current1.right.parent=current2;
                    }
                    current1.right=current2;
                    current2.parent=current1;
                }
                updateHeight(this.FindRoot());
                return;
            }
            else{
                current1=current1.parent;
            }
        }
        }
    }
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree a = this.FindRoot();
        if(a!=null){
        AVLTree b= new AVLTree(address,size,key);
        b.height=1;
        while(true){
            if(key<=a.key){
                if
                (a.left==null) {
                    a.left=b;
                    b.parent=a;
                    updateHeight(this.FindRoot());
                    checkBalance(b);
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
                    updateHeight(this.FindRoot());
                    checkBalance(b);
                    return b;
                }
                else{
                    a=a.right;
                }
            }
        }
    }
        else{
            AVLTree b= new AVLTree(address,size,key);
            b.height=1;
            this.right=b;
            b.parent=this;
            return b;
        }
    }
    private AVLTree successor(){
        AVLTree a = this;
        if(a.right!=null){
            a=a.right;
        }
        while(a.left!=null){
            a=a.left;
        }
        return a;
    }
    private AVLTree checkBalanceDel(AVLTree x){
        AVLTree current1=x;
        while(true){
        if(balance(x)>1){
            AVLTree current2=current1.left;
            if(balance(current2)>=0){
                AVLTree current3=current2.left;
                current1.left=current2.right;
                if(current2.right!=null){
                    current2.right.parent=current1;
                }
                current2.right=current1;
                if(current1.parent.left==current1){
                    current1.parent.left=current2;
                }
                else{
                    current1.parent.right=current2;
                }
                current2.parent=current1.parent;
                current1.parent=current2;
                updateHeight(this.FindRoot());
                return current2;

            }
            else{
                AVLTree current3=current2.right;
                current1.left=current3.right;
                if(current3.right!=null){
                    current3.right.parent=current1;
                }                
                current3.right=current1;
                if(current1.parent.left==current1){
                    current1.parent.left=current3;
                }
                else{
                    current1.parent.right=current3;
                }
                current3.parent=current1.parent;
                current1.parent=current3;
                current2.right=current3.left;
                if(current3.left!=null){
                    current3.left.parent=current2;
                }
                current3.left=current2;
                current2.parent=current3;
                updateHeight(this.FindRoot());
                return current3;
            }
        }
        if(balance(x)<-1){
            AVLTree current2=current1.right;
            if(balance(current2)>=0){
                AVLTree current3=current2.right;
                current1.right=current2.left;
                if(current2.left!=null){
                    current2.left.parent=current1;
                }
                current2.left=current1;
                if(current1.parent.right==current1){
                    current1.parent.right=current2;
                }
                else{
                    current1.parent.left=current2;
                }
                current2.parent=current1.parent;
                current1.parent=current2;
                updateHeight(this.FindRoot());
                return current2;


            }
            else{
                AVLTree current3=current2.left;
                current1.right=current3.left;
                if(current3.left!=null){
                    current3.left.parent=current1;
                }
                current3.left=current1;
                if(current1.parent.right==current1){
                    current1.parent.right=current3;
                }
                else{
                    current1.parent.left=current3;
                }
                current3.parent=current1.parent;
                current1.parent=current3;
                current2.left=current3.right;
                if(current3.right!=null){
                    current3.right.parent=current2;
                }
                current3.right=current2;
                current2.parent=current3;
                updateHeight(this.FindRoot());
                return current3;
            }
        }
        else{
            current1=current1.parent;
            if(current1.parent==null){
                return current1.right;
            }
        }
        }

    }
    public boolean Delete(Dictionary e)
    {
        AVLTree a = this;
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
                        if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(a.parent.parent!=null){
                            a=checkBalanceDel(a.parent);
                        }
                        return true;
                    }
                    else{
                        a.parent.right=null;
                        if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(a.parent.parent!=null){
                            a=checkBalanceDel(a.parent);
                        }
                        return true;
                    }
                }
                else if(a.left!=null&&a.right!=null){
                    AVLTree b = a.successor();
                    a.key=b.key;
                    a.size=b.size;
                    a.address=b.address;
                    if(b.left==null && b.right==null){
                    if(b.parent.left==b){
                        b.parent.left=null;
                        if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(b.parent.parent!=null){
                            b=checkBalanceDel(b.parent);
                        }
                        return true;
                    }
                    else{
                        b.parent.right=null;
                        if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(b.parent.parent!=null){
                            b=checkBalanceDel(b.parent);
                        }
                        return true;
                    }
                }
                    else{
                    if(b.parent.left==b){
                        if(b.left!=null){
                            b.parent.left=b.left;
                            b.left.parent=b.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(b.parent.parent!=null){
                            b=checkBalanceDel(b.parent);
                        }
                            return true;
                        }
                        else{
                            b.parent.left=b.right;
                            b.right.parent=b.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(b.parent.parent!=null){
                            b=checkBalanceDel(b.parent);
                        }
                            return true;
                        }
                    }
                    else{
                        if(b.left!=null){
                            b.parent.right=b.left;
                            b.left.parent=b.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(b.parent.parent!=null){
                            b=checkBalanceDel(b.parent);
                        }
                            return true;
                        }
                        else{
                            b.parent.right=b.right;
                            b.right.parent=b.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(b.parent.parent!=null){
                            b=checkBalanceDel(b.parent);
                        }
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
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(a.parent.parent!=null){
                            a=checkBalanceDel(a.parent);
                        }
                            return true;
                        }
                        else{
                            a.parent.left=a.right;
                            a.right.parent=a.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(a.parent.parent!=null){
                            a=checkBalanceDel(a.parent);
                        }
                            return true;
                        }
                    }
                    else{
                        if(a.left!=null){
                            a.parent.right=a.left;
                            a.left.parent=a.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(a.parent.parent!=null){
                            a=checkBalanceDel(a.parent);
                        }
                            return true;
                        }
                        else{
                            a.parent.right=a.right;
                            a.right.parent=a.parent;
                            if(this.parent!=null){
                            updateHeight(this);
                        }
                        else{
                            updateHeight(this.right);
                        }
                        while(a.parent.parent!=null){
                            a=checkBalanceDel(a.parent);
                        }
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
        
    public AVLTree Find(int key, boolean exact)
    {
        if(exact==true){
            AVLTree a = this;
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
            AVLTree a = this;
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

    public AVLTree getFirst()
    {
        if(!this.isEmpty()){
            AVLTree a = this;
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

    public AVLTree getNext()
    { 
        if(!this.isEmpty()){
            AVLTree a = this;
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
    //    AVLTree a = this.FindRoot();
    //    if(a.parent!=null || a.left!=null){
    //        return false;
    //    }
    //    else{
    //        return true;
    //    }
    //}
    private boolean sanitycheck(AVLTree a)
    {
        if(a==null){
            return true;
        }
        if((a.left!=null && a.left.key>a.key)||(a.right!=null && a.right.key<=a.key)||(balance(a)<-1)||(balance(a)>1)){
            return false;
        }
        else{
            return (sanitycheck(a.left) && sanitycheck(a.right));
        }
    }
    public boolean sanity()
    { 
        AVLTree a = this;
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


