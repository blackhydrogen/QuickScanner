import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


class QuickScanner {
	private BufferedInputStream bis;
	private int cur = -2;
	public QuickScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public String next() {
		try {
			return exNext();
		} catch(IOException e) {
			return null;
		}
	}

	public StringBuilder nextSB() {
		try {
			return exNextSB();
		} catch(IOException e) {
			return null;
		}
	}

	public String nextLine() {
		try {
			return exNextLine();
		} catch(IOException e) {
			return null;
		}
	}

	public StringBuilder nextLineSB() {
		try {
			return exNextLineSB();
		} catch(IOException e) {
			return null;
		}
	}

	public int nextInt() {
		try {
			return exNextInt();
		} catch(IOException e) {
			return -1;
		}
	}

	public long nextLong() {
		try {
			return exNextLong();
		} catch(IOException e) {
			return -1L;
		}
	}

	public double nextDouble() {
		try {
			return exNextDouble();
		} catch(IOException e) {
			return -1.0;
		}
	}

	public StringBuilder exNextSB() throws EOFException, IOException {
		while (cur <= 32) {
			cur = bis.read();
			if (cur == -1) throw new EOFException();
		}

		StringBuilder sb = new StringBuilder();

		while (cur > 32) {
			sb.append((char)cur);
			cur = bis.read();
		}

		return sb;
	}

	public String exNext() throws EOFException, IOException {
		return exNextSB().toString();
	}

	public String exNextLine() throws EOFException, IOException {
		return exNextLineSB().toString();
	}

	public StringBuilder exNextLineSB() throws EOFException, IOException {
		if(cur == -2)
			cur = bis.read();

		if (cur == -1) throw new EOFException();

		StringBuilder sb = new StringBuilder(100);

		while (cur != 13 && cur != 10 && cur != -1) {
			sb.append((char)cur);
			cur = bis.read();
		}

		if(cur == 13) {
			// handle \r\n's
			cur = bis.read();
			if(cur == 10) {
				cur = -2;
			}
		}
		else if(cur == 10) {
			cur = -2;
		}

		return sb;
	}

	public int exNextInt() throws EOFException, IOException {
		int result = 0;

		while ((cur < 48 || cur > 57) && cur != 45) {
			cur = bis.read();
			if (cur == -1) throw new EOFException();
		}

		boolean negate = false;
		if (cur == 45) {
			negate = true;
			cur = bis.read();
		}

		while (cur >= 48 && cur <= 57) {
			result = result * 10 + (cur - 48);
			cur = bis.read();
		}

		if (negate) {
			return -result;
		}
		return result;
	}

	public long exNextLong() throws EOFException, IOException {
		long result = 0;

		while ((cur < 48 || cur > 57) && cur != 45) {
			cur = bis.read();
			if (cur == -1) throw new EOFException();
		}

		boolean negate = false;
		if (cur == 45) {
			negate = true;
			cur = bis.read();
		}

		while (cur >= 48 && cur <= 57) {
			result = result * 10L + (cur - 48L); //cur auto-upgrade to long from int
			cur = bis.read();
		}

		if (negate) {
			return -result;
		}
		return result;
	}

	public double exNextDouble() throws EOFException, IOException {
		String str = exNext();

		return Double.parseDouble(str);
	}
}
