package com.mininh.flappy.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

	private BufferUtils() {

	}

	public static ByteBuffer createByteBuffer(byte[] array) {
		ByteBuffer resultado = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());

		resultado.put(array).flip();

		return resultado;
	}

	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer resultado = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

		resultado.put(array).flip();

		return resultado;
	}
	
	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer resultado = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();

		resultado.put(array).flip();

		return resultado;
	}

}
