package adapt;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator;
import org.apache.commons.math3.analysis.interpolation.HermiteInterpolator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

public class Main {

	
	public static void main(String[] args) throws Exception {
		
		List<Double> values = new ArrayList<>();
		Reader in = new FileReader("data/cost.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
		for (CSVRecord record : records) {
		    Double value = Double.valueOf(record.get(4)); // Close
		    values.add(value);
		}
		Collections.reverse(values);
		
		List<Observation> observations = new ArrayList<Observation>();
		List<WeightedObservedPoint> points = new ArrayList<WeightedObservedPoint>();
		int i = 0;
		for(Double value: values) {
			observations.add(new Observation(i++, value));
			System.out.println(value);
			points.add(new WeightedObservedPoint(1.0, i, value));
		}
		
		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(1);
		double[] coefficients = fitter.fit(points);
		for(double co: coefficients) {
//			System.out.println(co);
		}
		
		fitter = PolynomialCurveFitter.create(3);
		coefficients = fitter.fit(points);
		for(double co: coefficients) {
			System.out.println(co);
		}
		
		PolynomialFunction function = new PolynomialFunction(coefficients);
		System.out.println("Y = " + function.value(0));
		System.out.println("Y' = " +function.derivative().value(0));
				
		UnivariateInterpolator interpolator = new SplineInterpolator();
		UnivariateFunction function2 = interpolator.interpolate(new double[] {0.0,1.0,2.0},new double[] {1.0,-1.0,2.0});
		
	}
}
