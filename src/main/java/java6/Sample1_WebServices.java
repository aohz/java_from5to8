package java6;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

public class Sample1_WebServices {

	public static void main(String... args) {

		// To access the wsdl file go to -> http://localhost:8080/WebServiceExample/circlefunctions?wsdl
		Endpoint.publish("http://localhost:8080/WebServiceExample/circlefunctions", new CircleFunctions());

	}
}

@WebService
class CircleFunctions {
	public double getArea(double r) {
		return java.lang.Math.PI * (r * r);
	}

	public double getCircumference(double r) {
		return 2 * java.lang.Math.PI * r;
	}
}
