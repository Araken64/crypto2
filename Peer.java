import java.util.Arrays;

public class Peer {
    protected String _name;
    protected Peer   _friend;
    private java.security.KeyPair _keypair;
    
    public Peer(String name, java.security.KeyPair keypair) {
        _name   = name;
	    _friend = null;
	    _keypair = keypair;
    }

    public Peer(String name) {
        _name   = name;
        _friend = null;
        _keypair = null;
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
        String strMsg = new String(msg);
        System.out.println("["+_name+"] \""+ strMsg +"\" ->");
        _friend.receive(crypter(strMsg));
    }
    
    public void receive(byte[] msg) {
        String stringMsg = decrypter(msg);
        System.out.println("["+_name+"] -> \""+ stringMsg +"\"");
    }

    public byte[] crypter(String msg) {
        try {
            javax.crypto.Cipher enc = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
            enc.init(javax.crypto.Cipher.ENCRYPT_MODE, _keypair.getPrivate());
            enc.update(msg.getBytes());
            return enc.doFinal();
        } catch (java.security.NoSuchAlgorithmException | java.security.InvalidKeyException | javax.crypto.IllegalBlockSizeException | javax.crypto.BadPaddingException | javax.crypto.NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return msg.getBytes();
    }

    private String decrypter(byte[] buffer) {
        try {
            javax.crypto.Cipher dec = javax.crypto.Cipher.getInstance("RSA/ECB/PKCS1Padding");
            dec.init(javax.crypto.Cipher.DECRYPT_MODE, _keypair.getPublic());
            dec.update(buffer);
            return (new String(dec.doFinal()));
        } catch (java.security.NoSuchAlgorithmException | javax.crypto.NoSuchPaddingException | java.security.InvalidKeyException | javax.crypto.IllegalBlockSizeException | javax.crypto.BadPaddingException e) {
            e.printStackTrace();
        }
        return Arrays.toString(buffer);
    }
}
