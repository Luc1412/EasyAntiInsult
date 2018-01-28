package de.luc1412.eai.utils.tcp;

import de.luc1412.eai.utils.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TCPManager {

	private final int PORT = 10;
	private final String SPLIT = "@QG+?nA63M9YdKg&?uz$m5snD+3HMV@$BdtXJcHVJZjjm4z9^Q#TrP8&#=%qAkH&@U=d2TUXrmnWA!NENL8ey?AL*kK39EV9U+my9?9XzgMF*tw&kZkxdp3S8ep4KB!y7hXbDBDnC$WfFGk9d7gyr-uS%UwJzUJmYu3$VVWBe#QdwE#vjh&7VqkDP$TW#6ys#&@D3e-_$d!4s5=qsH+679Fv-Fb-L!T_VLx2Z_yyrJSN6J_5ayuZR^pJnC9j8N5uLuc1412";
	private Socket socket;

	public int checkWord(String word, Database... databases) {
		try {
			socket = new Socket("Luc1412.tk", PORT);

			PrintStream out = new PrintStream(socket.getOutputStream(), true);

			StringBuilder builder = new StringBuilder();
			for (Database database : databases) {
				if (builder.length() == 0) {
					builder.append(database.getValue());
					continue;
				}
				builder.append(":");
				builder.append(database.getValue());
			}

			String request = "checkWord" + SPLIT + word + SPLIT + builder.toString();
			out.println(request);

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return Integer.parseInt(input.readLine());
		} catch (IOException ex) {
			ex.printStackTrace();
			/*
			TODO: Change to normal exep and say that Server n/a
			 */
		} finally {
			try {
				if (socket != null) {
					socket.close();
					socket = null;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return -1;
	}

	public boolean checkDonator() {
		try {
			socket = new Socket("Luc1412.tk", PORT);

			PrintStream out = new PrintStream(socket.getOutputStream());
			out.println("donatorEAI");

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return Boolean.parseBoolean(input.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}


	/*public int checkWord(String word, Database... databases) {
		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.setReceiveTimeOut(1000);
		requester.setLinger(0);

		requester.connect("tcp://Luc1412.tk:" + FIRST_REQ_PORT);

		StringBuilder builder = new StringBuilder();
		for(Database database : databases) {
			if (builder.length() == 0) {
				builder.append(database.getValue());
				continue;
			}
			builder.append(":");
			builder.append(database.getValue());
		}

		String request = EasyAntiInsult.getUtils().getCurrentIp(true) + SPLIT + word + SPLIT + builder.toString() ;
		requester.send(request);

		startVerifyListener();

		requester.close();
		context.close();

		context = ZMQ.context(1);

		ZMQ.Socket responder = context.socket(ZMQ.REP);
		responder.bind("tcp://*:" + RESPONSE_PORT);
		responder.setLinger(0);

		return Integer.parseInt(responder.recvStr());
	}

	public boolean checkDonator() {
		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.setReceiveTimeOut(1000);
		requester.setLinger(0);

		requester.connect("tcp://Luc1412.tk:" + FIRST_REQ_PORT);

		String request = EasyAntiInsult.getUtils().getCurrentIp(true) + SPLIT + "donatorEAI" ;
		requester.send(request);

		startVerifyListener();

		requester.close();
		context.close();

		context = ZMQ.context(1);

		ZMQ.Socket responder = context.socket(ZMQ.REP);
		responder.bind("tcp://*:" + RESPONSE_PORT);
		responder.setLinger(0);

		return Boolean.parseBoolean(responder.recvStr());
	}

	private void startVerifyListener() {
		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket responder = context.socket(ZMQ.REP);
		responder.bind("tcp://*:" + VERIFY_REQUEST_PORT);
		responder.setLinger(0);

		String request = responder.recvStr();

		if (request.startsWith("verifyReq")) {
			int verifyPin = Integer.parseInt(request.split(":")[1]);
			try {
				verifyAccept(verifyPin);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		responder.close();
		context.close();
	}

	private void verifyAccept(int verifyPin) throws UnknownHostException {
		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.setReceiveTimeOut(1000);
		requester.setLinger(0);

		requester.connect("tcp://Luc1412.tk:" + VERIFY_ANSWER_PORT);

		String request = InetAddress.getLocalHost().getHostAddress() + ":" + verifyPin;
		requester.send(request);
	}

}
*/
