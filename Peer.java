public class Peer {
    protected String _name;
    protected Peer   _friend;
    
    public Peer(String name) {
        _name   = name;
	_friend = null;
    }
    
    private void connect(Peer p) {
        _friend = p;
    }
    
    public void bind(Peer p) {
        _friend = p;
        p.connect(this);
    }
    
    public void send(String msg) {
        this.send(msg.getBytes());
    }
    
    public void send(byte[] msg) {
        System.out.println("["+_name+"] \""+new String(msg)+"\" ->");
        _friend.receive(msg);
    }
    
    public void receive(byte[] msg) {
        System.out.println("["+_name+"] -> \""+new String(msg)+"\"");
    }
}
