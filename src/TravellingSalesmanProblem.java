import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class TravellingSalesmanProblem {

	public static void main(String[] args)
	{
		ArrayList<City> listOfCities = TSPLibLoader.loadTSPLib("rl5915.tsp");
		double route = routeLength(listOfCities);
		System.out.println(route);

		ArrayList<City> listOfCitiesAfterNearestNeighbour;
		double result = 0.0;
        listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
		route = routeLength(listOfCitiesAfterNearestNeighbour);
		result += route;
		System.out.println(result);

		result = 0.0;

		ArrayList<City> listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
		route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
		result += route;

		System.out.println(result);

        listOfCities = TSPLibLoader.loadTSPLib("berlin52.tsp");
        route = routeLength(listOfCities);
        System.out.println(route);

        result = 0.0;
        listOfCitiesAfterNearestNeighbour = nearestNeighbour(listOfCities);
        route = routeLength(listOfCitiesAfterNearestNeighbour);
        result += route;
        System.out.println(result);

        result = 0.0;

        listOfCitiesAfterNearestThreeNeighbours = nearestThreeNeighbours(listOfCities);
        route = routeLength(listOfCitiesAfterNearestThreeNeighbours);
        result += route;

        System.out.println(result);

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
		System.out.println(cityList.size());
		System.out.println(result.size());
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
		System.out.println(cities.size());
		System.out.println(result.size());
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
