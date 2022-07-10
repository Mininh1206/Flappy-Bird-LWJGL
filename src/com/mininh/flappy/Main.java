package com.mininh.flappy;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.mininh.flappy.input.Input;

public class Main implements Runnable {

	private int width = 1280;
	private int height = 720;

	private Thread thread;
	boolean running = false;

	private long window;

	public void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}

	public void run() {
		init();
		
		while (running) {
			update();
			render();
			
			if (glfwWindowShouldClose(window)) {
				running = false;
			}
		}
	}

	private void init() {
		if (!glfwInit()) {
			return;
		}

		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		window = glfwCreateWindow(width, height, "Flappy Bird", NULL, NULL);

		if (window == NULL) {
			return;
		}
		
		GLFWVidMode monitor = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (monitor.width() - width) / 2, (monitor.height() - height) / 2);
		
		glfwSetKeyCallback(window, new Input());
		
		glfwMakeContextCurrent(window);

		glfwSwapInterval(1);

		glfwShowWindow(window);
		
		GL.createCapabilities();

		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		System.out.println(glfwGetVersionString());
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
	}

	private void update() {
		glfwPollEvents();
		if (Input.keys[GLFW_KEY_SPACE]) {
			System.out.println("hola");
		}
		if (Input.keys[GLFW_KEY_ESCAPE]) {
			glfwSetWindowShouldClose(window, true);
		}
	}

	public static void main(String[] args) {
		new Main().start();
	}
}
