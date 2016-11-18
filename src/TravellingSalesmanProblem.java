import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TravellingSalesmanProblem {

	public static void main(String[] args)
	{
		ArrayList<City> listOfCities = TSPLibLoader.loadTSPLib("rl5915.tsp");
		System.out.println(listOfCities.size());
		ArrayList<City> listOfCitiesAfterNearestNeighbour;
		ArrayList<City> listOfCitiesAfterNearestThreeNeighbours;
		double route = routeLength(listOfCities);
		double result = 0.0;
		System.out.println(route);
		long start = 0;
		long end = 0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestNeighbour);
			result += route;
		}

		result = result/500;
		System.out.println("Average rute length for Nearest Neighbour for rl5915.tsp " + result);
		System.out.println("Time for Nearest Three Neighbours for rl5915.tsp" + end + "ms");

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
			result += route;
		}

		result = result/500;
		System.out.println("Average route length for Nearest Three Neighbours for rl5915.tsp " + result);
		System.out.println("Time for Nearest Three Neighbours for rl5915.tsp" + end + "ms");

        listOfCities = TSPLibLoader.loadTSPLib("berlin52.tsp");
        route = routeLength(listOfCities);

        result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestNeighbour);
			result += route;
		}

		result = result/500;

        System.out.println("Average route length for nearest neighbour for Berlin52 " + result);
		System.out.println("Time for nearest neighbour for Berlin52 " + end + "ms");

        result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
			result += route;
		}
		result = result/500;

        System.out.println("Average route length for nearest three neighbours for Berlin52 " +result);
		System.out.println("Time for nearest three neighbours for berling52 " + end + "ms");

		listOfCities = TSPLibLoader.loadTSPLib("rl5934.tsp");
		route = routeLength(listOfCities);

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestNeighbour);
			result += route;
		}

		result = result/500;

		System.out.println("Average route length for nearest neighbour for rl5934 " + result);
		System.out.println("Time for nearest neighbour for rl5934 " + end + "ms");

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
			result += route;
		}
		result = result/500;

		System.out.println("Average route length for nearest three neighbours for rl5934 " +result);
		System.out.println("Time for nearest three neighbours for rl5934 " + end + "ms");

		listOfCities = TSPLibLoader.loadTSPLib("pr1002.tsp");
		route = routeLength(listOfCities);

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestNeighbour);
			result += route;
		}

		result = result/500;

		System.out.println("Average route length for nearest neighbour for pr1002 " + result);
		System.out.println("Time for nearest neighbour for pr1002 " + end + "ms");

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
			result += route;
		}
		result = result/500;

		System.out.println("Average route length for nearest three neighbours for pr1002 " +result);
		System.out.println("Time for nearest three neighbours for pr1002 " + end + "ms");

		listOfCities = TSPLibLoader.loadTSPLib("pcb3038.tsp");
		route = routeLength(listOfCities);

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestNeighbour);
			result += route;
		}

		result = result/500;

		System.out.println("Average route length for nearest neighbour for pcb3038 " + result);
		System.out.println("Time for nearest neighbour for pcb3038 " + end + "ms");

		result = 0.0;

		for(int i = 0; i < 500; i++) {
			start = System.currentTimeMillis();
			listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
			end = System.currentTimeMillis() - start;
			route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
			result += route;
		}
		result = result/500;

		System.out.println("Average route length for nearest three neighbours for pcb3038 " +result);
		System.out.println("Time for nearest three neighbours for pcb3038 " + end + "ms");

	}
	
	public static double routeLength(ArrayList<City> cities)
	{
		//Calculate the length of a TSP route held in an ArrayList as a set of Points
		double result=0;//Holds the route length
		City prev = cities.get(cities.size()-1);
		//Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
		for(City city : cities)
		{
			//Go through each city in turn
			result += city.cityCoOrdinates.distance(prev.cityCoOrdinates);
			//get distance from the previous city
			prev = city;
			//current city will be the previous city next time
		}
		return result;
	}

	public static double routeLength2(ArrayList<Point2D> cities)
	{
		//Calculate the length of a TSP route held in an ArrayList as a set of Points
		double result=0;//Holds the route length
		Point2D prev = cities.get(cities.size()-1);
		//Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
		for(Point2D city : cities)
		{
			//Go through each city in turn
			result += city.distance(prev);
			//get distance from the previous city
			prev = city;
			//current city will be the previous city next time
		}
		return result;
	}
	
	public static ArrayList<City> nearestThreeNeighbours(ArrayList<City> cityList)
	{

		ArrayList<City> result = new ArrayList<City>();
		Random ran = new Random();
		int decider;
		City currentCity;
		City nextCity = new City();
		double distance;

		for(int i = 0; i < 20; i++)
		{
			double routeLength = Double.POSITIVE_INFINITY;
			ArrayList<City> potentialResult = new ArrayList<City>();
			ArrayList<City> cities = new ArrayList<City>(cityList);
			currentCity = cities.get(ran.nextInt(cities.size()));
			while (cities.size() > 0)
			{
				distance = Double.POSITIVE_INFINITY;
				currentCity.nearestNeighbour = cities.get(0);
				currentCity.secondNearestNeighbour = cities.get(0);
				currentCity.thirdNearestNeighbour = cities.get(0);
				potentialResult.add(currentCity);
				decider = ran.nextInt(3);

				for (City possible : cities)
				{
					if (getDistance(currentCity.cityCoOrdinates, possible.cityCoOrdinates) < distance)
					{
						currentCity.thirdNearestNeighbour = currentCity.secondNearestNeighbour;
						currentCity.secondNearestNeighbour = currentCity.nearestNeighbour;
						currentCity.nearestNeighbour = possible;
						distance = getDistance(currentCity.cityCoOrdinates, possible.cityCoOrdinates);
					}
				}
				if (decider == 2) {
					nextCity = currentCity.nearestNeighbour;
				}
				if (decider == 1) {
					nextCity = currentCity.secondNearestNeighbour;
				}
				if (decider == 0) {
					nextCity = currentCity.thirdNearestNeighbour;
				}
				currentCity = nextCity;
				cities.remove(currentCity);

			}
			if(routeLength(potentialResult) < routeLength)
			{
				result = potentialResult;
			}
		}
		return result;
	}

	public static ArrayList<City> nearestNeighbour(ArrayList<City> cities)
	{
		ArrayList<City> cityList = new ArrayList<City>(cities);
		ArrayList<City> result = new ArrayList<City>();
		City currentCity = cityList.get(0);
		City closestCity = new City();

		while(cityList.size() > 0)
		{
			result.add(currentCity);
			cityList.remove(currentCity);
			double distance = Double.POSITIVE_INFINITY;
			for(City possible: cityList)
			{
				if(getDistance(currentCity.cityCoOrdinates, possible.cityCoOrdinates) < distance)
				{
					closestCity = possible;
					distance = getDistance(currentCity.cityCoOrdinates, possible.cityCoOrdinates);
				}
			}
			currentCity = closestCity;
		}
		return result;
	}

	//Turns out that the farthest neighbour technique is a really bad solution to TSP.
	//It takes just as long as nearest neighbour to compute, but it gives back a really bad result.
	//Next I'm going to try and combine them, into 'Here, there, wherever you are Neighbour.'
	public static ArrayList<City> farthestNeighbour(ArrayList<City> cities)
	{
		ArrayList<City> cityList = new ArrayList<City>(cities);
		ArrayList<City> result = new ArrayList<City>();
		City currentCity = cityList.get(0);
		City farthestCity = new City();

		while(cityList.size() > 0)
		{
			result.add(currentCity);
			cityList.remove(currentCity);
			double distance = 0;
			for(City possible: cityList)
			{
				if(getDistance(currentCity.cityCoOrdinates, possible.cityCoOrdinates) > distance)
				{
					farthestCity = possible;
					distance = getDistance(currentCity.cityCoOrdinates, possible.cityCoOrdinates);
				}
			}
			currentCity = farthestCity;
		}
		System.out.println(cities.size());
		System.out.println(result.size());
		return result;
	}



	public static double getDistance(Point2D currentCity, Point2D possibleNextCity)
	{
		return currentCity.distance(possibleNextCity);
	}
}
