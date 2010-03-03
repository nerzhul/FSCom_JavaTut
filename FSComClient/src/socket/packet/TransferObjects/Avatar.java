package socket.packet.TransferObjects;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Avatar implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private ImageIcon img;
	
	public Avatar(Integer _uid, ImageIcon _img)
	{
		setUid(_uid);
		setImg(_img);
	}

	public void setUid(Integer uid) { this.uid = uid; }
	public Integer getUid() { return uid; }
	public void setImg(ImageIcon img) { this.img = img; }
	public ImageIcon getImg() { return img; }
}
