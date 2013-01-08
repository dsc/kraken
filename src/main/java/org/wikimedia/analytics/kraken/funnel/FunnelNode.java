package org.wikimedia.analytics.kraken.funnel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.regex.Pattern;

import org.wikimedia.analytics.kraken.exceptions.MalformedFunnelException;

public class FunnelNode extends Node{
	/** The params. */
	public HashMap<String, Pattern> params;

	/**
	 * Instantiates a new node. This constructor does not require the
	 * the useragent string as input.
	 *
	 * @param fproject the project variable as generated by the EventLogging extension.
	 * @param fnamespace the namespace variable as generated by the EventLogging extension.
	 * @param fpage the page variable as generated by the EventLogging extension.
	 * @param fevent the event variable as generated by the EventLogging extension.
	 * @throws MalformedFunnelException 
	 */
	public FunnelNode(String edge) throws MalformedFunnelException {
		super(edge);
		parseEdge(edge);
	}

	private void parseEdge(String edge) throws MalformedFunnelException {
		String[] data = edge.split(":");
		if (data.length != keys.size()) {
			throw new MalformedFunnelException("Each edge should contain the " +
		"following components (separated by a colon): " + Arrays.toString(keys.toArray()));
		}
		// We are iterating backwards, as the most significant parts are the funnel definiton come last.
		// Generate an iterator. Start just after the last element.
		ListIterator<String> li = keys.listIterator(keys.size());
		int i = data.length - 1;
		// Iterate in reverse.
		while(li.hasPrevious()) {
			String key = li.previous();
			Pattern value;
			if (data[i] != null) {
				value = Pattern.compile(data[i], Pattern.CASE_INSENSITIVE);
			} else {
				// Particular component is undefined so match all
				value = Pattern.compile("\\.*", Pattern.CASE_INSENSITIVE);
			}
			this.params.put(key, value);
			i--;
		}
	}
	
	public boolean equals(FunnelNode b) {
		if (this == b) return true;
		if (!(b instanceof FunnelNode)) return false;
		FunnelNode node = (FunnelNode)b;
		if (this.toString() == node.toString()) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		int e = 1;
		for (String key : keys) {
			Pattern value = this.params.get(key);
			if (value != null) {
				sb.append(value.toString());
			} else {
				sb.append(".");
			}
			if (e != params.size()) {
				sb.append(":");
			}
			e++;
		}
		return sb.toString();
	}

}
