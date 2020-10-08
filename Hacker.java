public class Hacker extends Peer {
    private Hacker _partner;

    public Hacker(Peer other) {
	super("bad boy 1");
	_partner = new Hacker(other, this);
    }

    public Hacker(Peer other, Hacker partner) {
	super("bad boy 2");
	_partner = partner;
	this.bind(other);
    }

    public void receive(byte[] msg) {
        System.out.println("["+_name+"] -> \""+new String(msg)+"\" ->");
	_partner._friend.receive(msg);
    }
}
