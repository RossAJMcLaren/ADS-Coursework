import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class TravellingSalesmanProblem {

	public static void main(String[] args)
	{
		ArrayList<Point2D> lolImGood = TSPLibLoader.loadTSPLib("rl5934.tsp");
		double route = routeLength(lolImGood);
		System.out.println(route);

		lolImGood = nearestNeighbour(lolImGood);
		route = routeLength(lolImGood);
		System.out.println(route);

		lolImGood = nearestNeighbour(lolImGood);
		route = routeLength(lolImGood);
		System.out.println(route);

		lolImGood = nearestNeighbour(lolImGood);
		route = routeLength(lolImGood);
		System.out.println(route);

		lolImGood = nearestNeighbour(lolImGood);
		route = routeLength(lolImGood);
		System.out.println(route);
	}
	
	public static double routeLength(ArrayList<Point2D> cities)
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
	
	public static ArrayList<Point2D> nearestNeighbour(ArrayList<Point2D> cities)
	{
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		Point2D currentCity = cities.remove(0);
		double distance;
		Point2D closestCity = cities.get(1);

		while(cities.size() > 0)
		{
			result.add(currentCity);
			distance = Double.POSITIVE_INFINITY;
			for (Point2D possible : cities)
			{
				if (getDistance(currentCity, possible) < distance)
				{
					closestCity = possible;
					distance = getDistance(currentCity, possible);
				}
			}
			cities.remove(closestCity);
			currentCity = closestCity;
		}
		return result;
	}

	public static double getDistance(Point2D currentCity, Point2D possibleNextCity)
	{
		return currentCity.distance(possibleNextCity);
	}

}
