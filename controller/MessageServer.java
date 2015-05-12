package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Message;

/*
 * * This is a simulated Message Server
 */
public class MessageServer implements Iterable<Message> {
	private Map<Integer, List<Message>> message;
	private List<Message> selected;

	public MessageServer() {
		selected = new ArrayList<Message>();
		message = new TreeMap<Integer, List<Message>>();
		List<Message> list = new ArrayList<Message>();
		list.add(new Message("The Cat is Missing",
				"Have You seen Felix anywhere?"));
		list.add(new Message("See you later ",
				"Are we still meeting in the park?"));
		message.put(0, list);

		list = new ArrayList<Message>();
		list.add(new Message("How about Dinner Later?",
				"HAre you doing anything later?"));
		message.put(1, list);
	}

	public void setSelectedServer(Set<Integer> servers) {
		selected.clear();
		for (Integer id : servers) {
			if (message.containsKey(id)) {
				selected.addAll(message.get(id));
			}
		}
	}

	public int getMessageCount() {
		return selected.size();
	}

	@Override
	public Iterator<Message> iterator() {
		// TODO Auto-generated method stub
		return new MessageIterator(selected);
	}

}

class MessageIterator implements Iterator {

	private Iterator<Message> iterator;

	public MessageIterator(List<Message> messages) {
		iterator = messages.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Object next() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

}
