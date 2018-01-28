

/**
 * Created by Luc1412 on 26.10.2017 at 22:50
 */
public class Test {

	/*
	public static void main(String... args) {
		String s = "Du HaCker Mach deine scheiß killaura aus!";
		System.out.println("Vorher: " + s);
		System.out.println("Nachher: " + replaceInsult(s));
	}

	public static boolean isUnicode(String message) {
		int[] out = new int[message.length()];
		for (int i = 0; i < message.length(); i++) {
			int a = message.charAt(i);
			out[i] = a;
		}
		Arrays.sort(out);
		System.out.println(Arrays.toString(out));
		return false;
	}

	public static String replaceInsult(String message) {

		List<String> blacklisted = Arrays.asList("Hacker:Skiller", "Killaura:Skillaura", "scheiß:nice");

		for (String aBlacklisted : blacklisted) {
			String[] s2 = aBlacklisted.split(":");
			if (s2.length > 2) System.err.println("error");
			message = message.replaceAll("(?i)" + s2[0], s2[1]);
		}

		return message;
	}

	public boolean isAdvertise(String message) {
		System.out.println("Start test for ads");
		message = message.toLowerCase();
		boolean isAd = false;

		for (String whitelistedString : whitelisted) {
			message = whitelistedString.replaceAll(whitelistedString, "");
			System.out.println(whitelistedString);
		}

		for (String blacklistedString : blacklisted) {
			System.out.println(blacklistedString);
			System.out.println(message);
			if (message.contains(blacklistedString)) {
				System.out.println("is ad");
				isAd = true;
			}
		}
		return isAd;
	}

	public static void main(String... args) {
		int a = 10;
		int b = 16;
		System.out.println("" + a * b);
	}


	public static void main(String... args) {
		System.out.println(isForbidden("GommeHD.net"));
	}

	static String[] blacklisted = new String[]{
			".de", ".d e", ". de", ". d e",
			".eu", ".e u", ". eu", ". e u",
			".com", ". com", ". c om", ". co m", ".c om", ".co m",
			".org", ". org", ". o rg", ". or g", ".o rg", ".or g",
			".net", ". net", ". n et", ". ne t", ".n et", ".ne t",
			".tk", ". tk", ".t k", ". t k",
			".ml", ". ml", ".m l", ". m l",
			".ga", ". ga", ".g a", ". g a",
			".cf", ". cf", ".c f", ". c f",
			".gq", ". gq", ".g q", ". g q",
			".at", ".a t", ". at", ". a t",
			".ch", ".c h", ". ch", ". c h",
			".me", ".m e", ". me", ". m e",
			".us", ".u s", ". us", ". u s",
			".tv", ".t v", ". tv", ". t v"};

	static List<String> whitelisted = Arrays.asList("GommeHD.net", "Google.de");


	public static boolean isForbidden(String message) {
		message = message.toLowerCase();
		boolean isAd = false;

		for (String whitelistedString : whitelisted) {
			message = whitelistedString.replaceAll(whitelistedString, "");
		}

		for (String blacklistedString : blacklisted) {
			if (message.contains(blacklistedString)) {
				System.out.println("is ad");
				isAd = true;
			}
		}
		return isAd;
	}
	*/

}
