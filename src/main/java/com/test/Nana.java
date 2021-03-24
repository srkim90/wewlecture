package com.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Nana extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
	 	OutputStream os = response.getOutputStream();
	 	PrintStream out = new PrintStream(os, true);
	 	out.println("Hello Servlet");
	}
}
