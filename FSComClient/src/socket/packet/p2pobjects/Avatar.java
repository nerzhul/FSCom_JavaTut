package socket.packet.p2pobjects;

import java.awt.Image;
import java.io.Serializable;

public class Avatar implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private Image img;
	
	public Avatar(Integer _uid, Image _img)
	{
		setUid(_uid);
		setImg(_img);
	}

	public void setUid(Integer uid) { this.uid = uid; }
	public Integer getUid() { return uid; }
	public void setImg(Image img) { this.img = img; }
	public Image getImg() { return img; }
}
