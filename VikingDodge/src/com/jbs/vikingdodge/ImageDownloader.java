package com.jbs.vikingdodge;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class ImageDownloader {

	public static int download(byte[] out, String url) {
		InputStream in = null;
		try {
			HttpURLConnection conn = null;
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(false);
			conn.setUseCaches(true);
			conn.connect();
			in = conn.getInputStream();
			int readBytes = 0;
			while (true) {
				int length = in.read(out, readBytes, out.length - readBytes);
				if (length == -1)
					break;
				readBytes += length;
			}
			return readBytes;
		} catch (Exception ex) {
			return 0;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static DownloadedImage downloadImage(String URL) {
		byte[] bytes = new byte[200 * 1024];
		int length = download(bytes, URL);
		return new DownloadedImage(bytes, length);
	}
	
	public static Texture imageFromDownload(DownloadedImage image) {
		byte[] bytes = image.getBytes();
		int numBytes = image.getNumBytes();
		if (numBytes != 0) {
			// load the pixmap, make it a power of two if necessary (not needed
			// for GL ES 2.0!)
			Pixmap pixmap = new Pixmap(bytes, 0, numBytes);
			int width = MathUtils.nextPowerOfTwo(pixmap.getWidth());
			int height = MathUtils.nextPowerOfTwo(pixmap.getHeight());
			final Pixmap potPixmap = new Pixmap(width, height, pixmap.getFormat());
			potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, pixmap.getWidth(), pixmap.getHeight());
			pixmap.dispose();

			return new Texture(potPixmap);
		}
		return null;
	}
	
	public static Texture processImage(byte[] bytes,int numBytes) {
		if (numBytes != 0) {
			// load the pixmap, make it a power of two if necessary (not needed
			// for GL ES 2.0!)
			Pixmap pixmap = new Pixmap(bytes, 0, numBytes);
			int width = MathUtils.nextPowerOfTwo(pixmap.getWidth());
			int height = MathUtils.nextPowerOfTwo(pixmap.getHeight());
			final Pixmap potPixmap = new Pixmap(width, height, pixmap.getFormat());
			potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, pixmap.getWidth(), pixmap.getHeight());
			pixmap.dispose();

			return new Texture(potPixmap);
		}
		return null;
	}
}
