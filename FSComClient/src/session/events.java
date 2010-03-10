package session;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import socket.Sender;
import socket.packet.ConnectData;
import socket.packet.handlers.Send_handler;
import socket.packet.handlers.sends.Disconnect_handler;
import socket.packet.handlers.sends.client_handlers.Answer_Invit_handler;
import socket.packet.handlers.sends.client_handlers.AvatarSender_handler;
import socket.packet.handlers.sends.client_handlers.StatusSender_handler;
import socket.packet.objects.Avatar;
import socket.packet.objects.ClientDatas;
import socket.packet.objects.IdAndData;
import socket.packet.objects.Message;
import thread.threading;
import thread.windowthread;
import windows.SwingExtendLib.SwingEL;
import windows.forms.form_communicate;
import windows.forms.form_inscription;
import windows.forms.onglet_communicate;
import misc.Log;
import misc.Misc;
/*
 * classe faisant toutes les intéractions client/serveur
 */
public class events {

	//on sauvegarde le status de l'utilisateur
	public static void StoreStatus(Integer st)
	{
		Session.setStatus(st);
	}
	
	//on sauvegarde la liste des contacts de l'utilisateur
	public static void StoreContacts(Vector<contact> packet)
	{
		Session.ClearContacts();
		for(contact ct: packet)
			Session.CreateNewContact(ct);
	}

	//on sauvegarde les groupes de l'utilisateur
	public static void StoreGroups(Vector<group> packet) 
	{
		Session.ClearGroups();
		
		Session.CreateNewGroup(new group(0,"Autres contacts"));//définition du groupe par défaut où sont ajouté les contacts
		for(group gr:packet)
			Session.CreateNewGroup(gr);//puis on créait les autres groupes
	}

	//on fait l'action correspondant à la déconnection d'un de nos contacts
	public static void ContactDisconnected(Object packet) 
	{
		if(Misc.isWrongType(packet, new Integer(0)))
			return;
		Integer st = Integer.decode(packet.toString());
		for(group g : Session.getGroups())//on cherche dans les groupes
			for(contact ct : g.getContacts())//puis dans les contacts du groupe
				if(ct.getCid().equals(st))
				{
					ct.setStatus(0);//on le passe en hors ligne
					windowthread.getFmConn().getPanContact().RefreshContactList();//on refresh la liste des contacts
					form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
					if(fmCom != null)
						fmCom.ChangeConversStatusForContact(ct.getCid());//on refresh les cadres dans les fenetres (si ouvert)
					return;
				}
	}
	
	//on fait l'action correspondant à la connection d'un de nos contacts
	public static void ContactConnected(Object packet) 
	{
		if(Misc.isWrongType(packet, new ConnectData("",0,"",0,"")))
			return;
		
		ConnectData cn = (ConnectData)packet;
		for(group g : Session.getGroups())//on cherche dans les groupes
			for(contact ct : g.getContacts())// on cherche dans les contacts
				if(ct.getCid().equals(cn.getUid()))//et si on trouve le contact
				{
					ct.setStatus(cn.getStatus());//on change son statut
					ct.setPseudo(cn.getPseudo());//on change le pseudo
					ct.setMsg_perso(cn.getPersoP());//on recupère la phrase perso
					windowthread.getFmConn().getPanContact().RefreshContactList();//on refresh la liste des contacts
					form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
					if(fmCom != null)
						fmCom.ChangeConversStatusForContact(ct.getCid());//on refresh la conversation si ouvert
					return;
				}
	}

	//on fait l'action correspondant au bloquage/débloquage d'un de nos contacts
	public static void BlockContact(Object packet) 
	{
		if(Misc.isWrongType(packet, new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)packet;
		Integer _uid = pck.getUid();
		Integer method = Integer.decode(pck.getDat());
		for(group g : Session.getGroups())//on parcourt les groupes
			for(contact ct : g.getContacts())//on parcourt les contacts
				if(ct.getCid().equals(_uid))// et si on trouve
				{
					ct.setBlocked((method == 1) ? true: false);// on le passe en bloqué ou debloqué selon la situation
					if(ct.isBlocked())
						JOptionPane.showMessageDialog(null, "Le contact " + ct.getPseudo() +  " a �t� bloqu�");//avec popup pour confirmer
					else
						JOptionPane.showMessageDialog(null, "Le contact " + ct.getPseudo() +  " a �t� d�bloqu�");
					windowthread.getFmConn().getPanContact().RefreshContactList();//et mise a jour de l'abre pour changer le statut

					return;
				}
	}

	//on fait l'action correspondant à la reception d'un message d'un de nos contacts
	public static void RecvMsg(Object packet) 
	{
		if(Misc.isWrongType(packet, new Message(null,null)))
			return;
		
		Message msg = (Message) packet;
		Integer _uid = msg.getDest();
		form_communicate fmSpeak = windowthread.getFmConn().getPanContact().getComm();
		if(fmSpeak == null)//si il n'y a pas de fenetre de conversation
		{
			windowthread.getFmConn().getPanContact().setComm(new form_communicate()); //on en créait une
			fmSpeak = windowthread.getFmConn().getPanContact().getComm();
		}
		
		onglet_communicate ongl = fmSpeak.GetContactConvers(_uid);
		if(ongl == null)//si il n'y pas d'onglet
		{
			contact ct = Session.getContactByUid(_uid);
			if(ct != null)
			{
				windowthread.getFmConn().getPanContact().getComm().AddTab(ct);//on en créait un avec le contact
				ongl = fmSpeak.GetContactConvers(_uid);
				ongl.WriteMsg(msg.getMsg());//et on écrit le message envoyé
			}
			else
				Log.outError("Contact" + _uid + " not exist for client");
		}
		else
			ongl.WriteMsg(msg.getMsg());// et si il y a déja un onglet on écrit seulement le message dans l'onglet
	}

	//on fait l'action correspondant au changement de statut d'un de nos contacts
	public static void ContactModifyStatus(Object packet) 
	{
		if(Misc.isWrongType(packet, new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)packet;
		if(pck.getUid().equals(0))//si c'est nous
		{
			Session.setStatus(Integer.decode(pck.getDat()));
			form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
				if(fmCom != null)//si on a une/plusieurs conversations en cours
					fmCom.ChangeAllMyStatusBorder();//on change notre bordure dans les onglets
			windowthread.getFmConn().getPanContact().ChangeBorderStatus();//et dans la fenetre principale (liste des contacts)
		}
		else //et si c'est un de nos contacts
		{
			for(group g : Session.getGroups())//on parcourt les groupes
				for(contact ct : g.getContacts())//on parcourt les contacts
					if(ct.getCid().equals(pck.getUid()))//jusqu'à le trouver
					{
						ct.setStatus(Integer.decode(pck.getDat()));
						windowthread.getFmConn().getPanContact().RefreshContactList();//on refresh l'abre contenant la liste des contacts
						form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
						if(fmCom != null)//et si on a un onglet d'ouvert avec se contact
							fmCom.ChangeConversStatusForContact(ct.getCid());//on change sa bordure pour correspondre au statut
						return;
					}
		}
	}

	//on fait l'action correspondant au changement de pseudo d'un de nos contacts
	public static void ContactModifyPseudo(Object packet) 
	{
		if(Misc.isWrongType(packet,new IdAndData(null,null)))
			return;
		
		IdAndData pck = (IdAndData) packet;
		if(pck.getUid().equals(0))//si c'est nous
		{
			if(windowthread.getFmConn() != null)//et si on a la fenetre principale
				if(windowthread.getFmConn().getPanContact() != null)
					windowthread.getFmConn().getPanContact().ChPseudo(pck.getDat());//on change notre pseudo dans la page principale
		}
		else
		{
			for(group g : Session.getGroups())// on parcourt les groupes
				for(contact ct : g.getContacts())// on parcourt les contacts
					if(ct.getCid().equals(pck.getUid()))//et quand on trouve le contact
					{
						ct.setPseudo(pck.getDat());
						windowthread.getFmConn().getPanContact().RefreshContactList();//on refresh l'arbre de la liste des contacts
						form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
						if(fmCom != null)//et si on a un onglet de conversation d'ouvert avec ce contact
							fmCom.ChangeConversTabTitle(pck.getUid(),pck.getDat());//on change son pseudo
						return;
					}
		}
	}

	//on fait l'action correspondant au changement de la phrase perso d'un de nos contacts
	public static void ContactModifyPmsg(Object packet) 
	{
		if(Misc.isWrongType(packet,new IdAndData(null,null)))
			return;
		
		IdAndData pck = (IdAndData) packet;
		if(pck.getUid().equals(0))
		{//si c'est nous
			if(windowthread.getFmConn() != null)
				if(windowthread.getFmConn().getPanContact() != null)//et que notre page principale existe
					windowthread.getFmConn().getPanContact().ChPPers(pck.getDat());//on change notre phrase dans notre page principale
		}
		else//si c'est un de nos contacts
		{
			for(group g : Session.getGroups())//on parcourt les groupes
				for(contact ct : g.getContacts())//on parcourt les contacts
					if(ct.getCid().equals(pck.getUid()))//et quand on trouve le contact
					{
						ct.setMsg_perso(pck.getDat());//on met a jour sa phrase perso
						windowthread.getFmConn().getPanContact().RefreshContactList();
						return;
					}
		}
	}

	//on fait l'action correspondant l'ajout d'un contact a notre liste
	public static void ContactAdded(Object packet) 
	{
		if(Misc.isWrongType(packet,new contact(null, 0, null, null, null, null, null, null)))
			return;
		contact ct = (contact)packet;
		if(ct == null)//si on ajoute un contact qui n'existe pas
		{
			Log.ShowPopup("Le contact ajout� n'existe pas !","Contact inexistant !", true);// on nous le dit
			return;
		}
		for(group g: Session.getGroups())
			if(g.getGid().equals(0))//on se place de le groupe pas défaut
			{
				g.AddContact(ct);//et on y ajoute notre contact
				windowthread.getFmConn().getPanContact().HardRefreshContactList();//et on recrait l'arbre de contact pour mettre a jour
				return;
			}
	}

	//on fait l'action correspondant à la suppression d'un de nos contacts
	public static void ContactDeleted(Object packet) 
	{
		if(Misc.isWrongType(packet,new Integer(0)))
			return;
		for(group g : Session.getGroups())//on parcourt les groupes
			for(contact ct : g.getContacts())//on parcourt les contacts
				if(ct.getCid().equals(Integer.decode(packet.toString())))//et quand on trouve le contact
				{
					g.getContacts().remove(ct);//on le retire du vecteur
					windowthread.getFmConn().getPanContact().HardRefreshContactList();//et on recrait l'arbre de contact pour mettre a jour
					return;
				}
	}

	//on fait l'action correspondant à la reception d'une invitation quand quelqu'un nous ajoute
	public static void RecvInvitation(Object packet) 
	{
		if(Misc.isWrongType(packet,new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)packet;
		//on affiche un popup et on stock la réponse
		Integer answer = JOptionPane.showConfirmDialog(null, pck.getDat() + " vous a ajout�, voulez vous l'accepter ?","Nouveau contact !",JOptionPane.YES_NO_CANCEL_OPTION);
		Answer_Invit_handler arh = new Answer_Invit_handler(pck.getUid(), answer);
		Log.outError(answer.toString());
		arh.Send();//et on envoi la réponse au serveur
	}

	//popup d'un message d'erreur
	public static void ConnectionError() 
	{
		Log.ShowPopup("Erreur de connexion avec le serveur","Erreur de connexion...", true);
	}

	//popup d'un message d'erreur
	public static void BadLoginInformations() 
	{
		Log.ShowPopup("Login incorrect","Erreur de connexion...", true);
	}

	//fonction permettant le switch de panel
	public static void ShowConnectedFrame() 
	{
		windowthread.SwitchPanel(2);
	}

	//fonction de déconnection du client en cours
	public static void DisconnectCurrentClient() 
	{
		Send_handler pck = new StatusSender_handler(0);
		pck.Send();		
		Disconnect_handler pck1 = new Disconnect_handler();
		pck1.Send();
		threading.StopSender();
		Sender.StopSocket();
	}

	//fonction stockant toutes les données d'un client
	public static void StoreAllDatas(Object packet) 
	{
		if(Misc.isWrongType(packet,new ClientDatas()))
			return;
		
		ClientDatas pck = (ClientDatas) packet;
		Session.setPerso_msg(pck.getPperso());
		Session.setPseudo(pck.getPseudo());
		events.StoreGroups(pck.GetMyGroups());
		events.StoreContacts(pck.GetMyContacts());
		Session.setStatus(pck.getStatus());
		
		AvatarSender_handler pkt = new AvatarSender_handler(new ImageIcon(SwingEL.scale(
				(new ImageIcon("avatar.png")).getImage())));
		pkt.Send();
	}

	//fonction pour l'ajout de groupe
	public static void GroupAdded(Object data) 
	{
		if(Misc.isWrongType(data,new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)data;
		group gr = new group(pck.getUid(), pck.getDat());
		Session.getGroups().add(gr);
		windowthread.getFmConn().getPanContact().HardRefreshContactList();	//on reconstruit l'arbre après l'ajout	
	}

	//fonction permettant la suppression de groupe
	public synchronized static void GroupDeleted(Object data) 
	{
		if(Misc.isWrongType(data,new Integer(0)))
			return;
		
		Integer _gid = Integer.decode(data.toString());
		if(_gid.equals(0))
			return;
		
		for(group g: Session.getGroups())//on parcourt les groupes
		{
			if(g.getGid().equals(_gid))//jusqu'a trouver le bon groupe
			{
				group move_gr = Session.getDefaultGroup();
				if(move_gr == null)
					return;

				for(int i=0;i<g.getContacts().size();i++)//on place tout les contacts qu'il contenait dans le groupe par defaut
				{
					contact ct = g.getContacts().get(i);
					move_gr.AddContact(ct);
					g.getContacts().remove(ct);
				}
				
				g.getContacts().clear();
				Session.getGroups().remove(g);//on enleve le groupe du vecteur
				
				windowthread.getFmConn().getPanContact().HardRefreshContactList();//on reconstruit l'arbre après la suppression
				return;
			}
		}
	}

	//fonction permettant de renommer un groupe
	public static void GroupRenamed(Object data) 
	{
		if(Misc.isWrongType(data,new IdAndData(0,"")))
			return;
		
		IdAndData pck = (IdAndData)data;
		Integer _gid = pck.getUid();
		String newName = pck.getDat();
		
		for(group g: Session.getGroups())//on cherche le groupe
		{
			if(g.getGid().equals(_gid))
			{
				g.setGname(newName);// et on lui met son nouveau nom
				windowthread.getFmConn().getPanContact().HardRefreshContactList();//puis on reconstruit l'arbre
				return;
			}
		}
	}

	//fonction permettant de s'inscire
	public static void CreateAccountAnswer(Object data) 
	{
		if(Misc.isWrongType(data,new Integer(0)))
			return;
		
		Integer res = Integer.decode(data.toString());
		form_inscription fmInsc = windowthread.getFmConn().getPanConnect().getFmInsc();
		if(fmInsc == null)
			return;
		threading.StopSender();
		if(res != 1)//selon la réponse du serveur, on affiche si le compte a été créé
		{
			JOptionPane.showMessageDialog(null,"Compte d�j� existant !");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Compte cr�� avec succ�s !");
			windowthread.getFmConn().getPanConnect().setFmInsc(null);
			fmInsc.dispose();
		}
	}

	//fonction permettant de changer d'avatar
	public static void ChangeContactAvatar(Object data) 
	{
		if(Misc.isWrongType(data,new Avatar(0,new ImageIcon())))
			return;
		
		Avatar av = (Avatar)data;
		if(av == null)
			return;
		
		Integer _uid = av.getUid();
		//si il n'y a pas d'image on met une image par defaut
		if(av.getImg() == null)
		{
			ImageIcon a = new ImageIcon ("cookie.jpg");
		    Image avatar = SwingEL.scale(a.getImage());
		    av.setImg(new ImageIcon(avatar));
		}
		
		ImageIcon img = av.getImg();
		form_communicate fmCom = windowthread.getFmConn().getPanContact().getComm();
		if(fmCom == null)
			return;
		//si un contact change d'avatar on met a jour l'onglet
		fmCom.ChangeContactAvatar(_uid,img);
	}
	
	//fonction permettant de changer un contact de groupe
	public synchronized static void ContactChangeGroup(contact ct,Integer gid) {
		for(group g: Session.getGroups()){
			for(int i=0;i<g.getContacts().size();i++){
				contact c = g.getContacts().get(i);
				if(ct.equals(c))//quand on trouve le contact
					g.getContacts().remove(c);//on le supprime de son ancien groupe
			}
		}
		
		for(group g: Session.getGroups()){
			if(g.getGid().equals(gid)){
				g.AddContact(ct);//et on l'ajoute a son nouveau groupe
			return;
			}
		}
			
	}
}
