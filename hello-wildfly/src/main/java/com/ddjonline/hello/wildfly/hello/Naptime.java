package com.ddjonline.hello.wildfly.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/naptime")
public class Naptime {

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("your slice of pi is " + pi_digits(20000)).build();
	}

	private static String pi_digits(int digits) {
		int scale = 10000;
		int array_init = 2000;
		StringBuffer pi = new StringBuffer();
		int[] arr = new int[digits + 1];
		int carry = 0;

		for (int i = 0; i <= digits; ++i)
			arr[i] = array_init;

		for (int i = digits; i > 0; i -= 14) {
			int sum = 0;
			for (int j = i; j > 0; --j) {
				sum = sum * j + scale * arr[j];
				arr[j] = sum % (j * 2 - 1);
				sum /= j * 2 - 1;
			}

			pi.append(String.format("%04d", carry + sum / scale));
			carry = sum % scale;
		}
		return pi.toString();
	}
}