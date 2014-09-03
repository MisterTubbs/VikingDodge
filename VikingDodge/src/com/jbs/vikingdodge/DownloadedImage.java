package com.jbs.vikingdodge;

public class DownloadedImage {
	
	private int numBytes;
	private byte[] bytes;
	
	public DownloadedImage(byte[] bytes, int numBytes) {
		this.bytes = bytes;
		this.numBytes = numBytes;
	}

	public int getNumBytes() {
		return numBytes;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
 }
