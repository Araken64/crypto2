public class Program02 {
    public static void main(String[] args) {
	Peer anna = new Peer("anna");
	Peer bob  = new Peer("bob");
	//anna.bind(bob);
	anna.bind(new Hacker(bob));

	anna.send("coucou");
	bob.send("hello");
    }
}
