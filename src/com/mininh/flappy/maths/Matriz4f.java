package com.mininh.flappy.maths;

import java.nio.FloatBuffer;

import com.mininh.flappy.utils.BufferUtils;

public class Matriz4f {

	public static final int TAMAÑO = 4 * 4;

	public float[] elementos = new float[TAMAÑO];

	public Matriz4f() {

	}

	public static Matriz4f identica() {
		Matriz4f resultado = new Matriz4f();

		resultado.elementos[0 + 0 * 4] = 1.0f;
		resultado.elementos[1 + 1 * 4] = 1.0f;
		resultado.elementos[2 + 2 * 4] = 1.0f;
		resultado.elementos[3 + 3 * 4] = 1.0f;

		return resultado;
	}

	public static Matriz4f ortogonal(float izquierda, float derecha, float abajo, float arriba, float cerca, float lejos) {
		Matriz4f resultado = identica();

		resultado.elementos[0 + 0 * 4] = 2.0f / (derecha - izquierda);
		resultado.elementos[1 + 1 * 4] = 2.0f / (arriba - abajo);
		resultado.elementos[2 + 2 * 4] = 2.0f / (lejos - cerca);

		resultado.elementos[0 + 3 * 4] = (izquierda + derecha) / (izquierda - derecha);
		resultado.elementos[1 + 3 * 4] = (abajo + arriba) / (abajo - arriba);
		resultado.elementos[2 + 3 * 4] = (cerca + lejos) / (cerca - lejos);

		return resultado;
	}

	public static Matriz4f transladar(Vector3f vector) {
		Matriz4f resultado = identica();

		resultado.elementos[0 + 3 * 4] = vector.x;
		resultado.elementos[1 + 3 * 4] = vector.y;
		resultado.elementos[2 + 3 * 4] = vector.z;

		return resultado;
	}

	public static Matriz4f rotar(float angulo) {
		Matriz4f resultado = identica();

		float radianes = (float) Math.toRadians(angulo);
		float cos = (float) Math.cos(radianes);
		float sin = (float) Math.sin(radianes);

		resultado.elementos[0 + 0 * 4] = cos;
		resultado.elementos[1 + 0 * 4] = sin;

		resultado.elementos[0 + 1 * 4] = -sin;
		resultado.elementos[1 + 1 * 4] = cos;

		return resultado;
	}

	public Matriz4f multiplicar(Matriz4f matriz) {
		Matriz4f resultado = new Matriz4f();

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				float suma = 0.0f;
				for (int e = 0; e < 4; e++) {
					suma += this.elementos[x + e * 4] * matriz.elementos[e + y * 4];
				}
				resultado.elementos[x + y * 4] = suma;
			}
		}
		
		return resultado;
	}
	
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(elementos);
	}

}
