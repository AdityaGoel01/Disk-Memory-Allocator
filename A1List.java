// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List node= new A1List(address,size,key);
        node.next=this.next;
        node.prev=this;
        this.next=node;
        node.next.prev=node;
        return node;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List current=this;
        boolean found = true;
        if(current.next!=null){
            while(current!=d){
            if(current.next.next==null){
                found=false;
                break;
            }
            current=current.next;
        }
        if(found==true){
            current.prev.next=current.next;
            current.next.prev=current.prev;
            return true;
        }
        }
            current=this;
            if(current.prev!=null){
                while(current!=d){
            if(current.prev.prev==null){
                found=false;
                break;
            }
            current=current.prev;
            }
            if(found==true){
                current.prev.next=current.next;
                current.next.prev=current.prev;
                return true;
            }
            }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        if(exact==true){
            A1List current=this;
            boolean found = true;
            if(current.next!=null){
                while(current.key!=k){
                if(current.next.next==null){
                    found=false;
                    break;
                }
                current=current.next;
            }
            if(found==true){
                return current;
            }
            }
                current=this;
                if(current.prev!=null){
                    while(current.key!=k){
                if(current.prev.prev==null){
                    found=false;
                    break;
                }
                current=current.prev;
                }
                if(found==true){
                    return current;
                }
                }
                return null;
        }
        else{
            A1List current=this;
            boolean found = true;
            if(current.next!=null){
                while(current.key<k){
                if(current.next.next==null){
                    found=false;
                    break;
                }
                current=current.next;
            }
            if(found==true){
                return current;
            }
            }
                current=this;
                if(current.prev!=null){
                    while(current.key<k){
                if(current.prev.prev==null){
                    found=false;
                    break;
                }
                current=current.prev;
                }
                if(found==true){
                    return current;
                }
                }
            return null;
        }
    }

    public A1List getFirst()
    {
        if(this.prev==null){
            if(this.next.key!=-1){
                return this.next;
            }
            else{
                return null;
            }
        }
        else{
            A1List current=this;
            while(current.prev.prev!=null){
                current=current.prev;
            }
            return current;
        }
    }
    
    public A1List getNext() 
    {
        if(this.next==null || this.next.key==-1){
            return null;
        } 
        else{
            return this.next;
        }
    }

    public boolean sanity()
    {
        A1List current=this;
        if(current.prev!=null){
            return false;
        }
        while(current.next!=null){
            if(current.next.prev!=current){
                return false;
            }
            current=current.next;
            }
        if(!(current.key==-1&&current.address==-1&&current.size==-1)){
            return false;
        }
        return true;
    }

}


