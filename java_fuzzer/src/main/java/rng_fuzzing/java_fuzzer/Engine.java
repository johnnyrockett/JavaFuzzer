package rng_fuzzing.java_fuzzer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import rng_fuzzing.java_fuzzer.Fuzz;

/**
 * Hello world!
 *
 */
public class Engine {
	@SuppressWarnings({ "rawtypes" })
	public static void run(Set<Method> methods) {
		Map<Class, Set<Method>> map = new HashMap<Class, Set<Method>>();

		// TODO: now that the runner fuzzes by method, don't need to sort by Class
		for (Method method : methods) {
			method.setAccessible(true);
			Class cls = method.getDeclaringClass();
			if (map.containsKey(cls)) {
				map.get(cls).add(method);
			} else {
				Set<Method> mth = new HashSet<Method>();
				mth.add(method);
				map.put(cls, mth);
			}
		}

		for (Map.Entry<Class, Set<Method>> entry : map.entrySet()) {
			Class key = entry.getKey();
			for (Method method : entry.getValue()) {
				Runner runner = new Runner(key, method);
				runner.start();
			}
		}

		// Create Chart
//		RadarChart chart = new RadarChartBuilder().width(500).height(500).title("Performance Breakdown").build();
//		
//		chart.setVariableLabels(
//	        new String[] {
//	          "Duration",
//	          "Code Coverage",
//	          "Crashes Found",
//	          "Timeouts Found",
//	          "Originality of Crashes",
//	          "Originality of Timeouts"
//	        });
//		
//		chart.addSeries("Monte Carlo", new double[] { 0.2, 1.0, 0.25, 0.44, 0.85, 0.19 });
//		chart.addSeries("Mutation Based", new double[] { 0.8, 0.79, 0.25, 0.44, 0.85, 0.19 });
//		chart.addSeries("Generation Based", new double[] { 0.52, 0.60, 0.74, 0.94, 0.26, 0.27 });
//		chart.addSeries("My own strategy", new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0 });
//		
//		// Show it
//		new SwingWrapper(chart).displayChart();

//		// Save it
//		BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
//
//		// or save it in high-res
//		BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300);
	}
}
