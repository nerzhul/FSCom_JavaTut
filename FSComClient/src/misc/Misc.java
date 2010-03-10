package misc;

public class Misc {

	private static String version;
	
	//fonction de vérification des types des objets
	public static boolean isWrongType(Object obj,Object obj2)
	{
		if(obj == null || obj.getClass().equals(obj2.getClass()))
			return false;
		else
			return true;
	}
	//fonction pour avoir la version du client
	public static void RegisterVersion(String v){
		version = v;
	}
	
	public static String GetVersion(){
		return version;
	}
}
