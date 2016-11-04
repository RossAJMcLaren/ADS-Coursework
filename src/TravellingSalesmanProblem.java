import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class TravellingSalesmanProblem {

	public static void main(String[] args)
	{
		ArrayList<Point2D> lolImGood = TSPLibLoader.loadTSPLib("rl5934.tsp");
		double route = routeLength(lolImGood);
		System.out.println(route);

		/*lolImGood = nearestNeighbour(lolImGood);
		route = routeLength(lolImGood);
		System.out.println(route);*/

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
		double bound = 10000;
		Point2D closestCity = cities.get(1);
		Point2D farthestCity = cities.get(1);
		Boolean close = true;

		while(cities.size() > 0)
		{
			result.add(currentCity);
			distance = Double.POSITIVE_INFINITY;
			for (Point2D possible : cities)
			{
				if (getDistance(currentCity, possible) < distance && getDistance(currentCity, possible) < bound)
				{
					closestCity = possible;
					close = true;
					distance = getDistance(currentCity, possible);
				}
				else
				{
					close = false;
					farthestCity = possible;
					distance = getDistance(farthestNeighbour(cities, currentCity), possible);
				}
			}
			System.out.println(cities.size());
			cities.remove(closestCity);
			cities.remove(farthestCity);
			if(close)
			{
				currentCity = closestCity;
			}
			else
			{
				currentCity = farthestCity;
			}
		}
		return result;
	}

	//Turns out that the farthest neighbour technique is a really bad solution to TSP.
	//It takes just as long as nearest neighbour to compute, but it gives back a really bad result.
	//Next I'm going to try and combine them, into 'Here, there, wherever you are Neighbour.'
	public static Point2D farthestNeighbour(ArrayList<Point2D> cities, Point2D city)
	{
		Point2D currentCity = city;
		double distance = 0;
		Point2D farthestCity = cities.get(1);

		for (Point2D possible : cities)
		{
			if (getDistance(currentCity, possible) > distance)
			{
				farthestCity = possible;
				distance = getDistance(currentCity, possible);
			}
		}
			currentCity = farthestCity;
		return farthestCity;
	}

	public static ArrayList<ArrayList<Point2D>> findCluster(ArrayList<Point2D> cities)
	{
		ArrayList<ArrayList<Point2D>> result = new ArrayList<ArrayList<Point2D>>();
		ArrayList<Point2D> cluster = new ArrayList<Point2D>();
		Point2D currentCity = farthestNeighbour(cities, cities.remove(0));
		double distance;
		double bounds;
		Point2D closestCity = cities.get(1);


		while(cities.size() > 0)
		{
			distance = Double.POSITIVE_INFINITY;
			for (Point2D possible : cities)
			{
				bounds = 500.0;
				if (getDistance(currentCity, possible) < distance && getDistance(currentCity, possible) < bounds)
				{
					closestCity = possible;
					distance = getDistance(currentCity, possible);
					cluster.add(possible);
				}
			}
			cities.remove(closestCity);
			currentCity = farthestNeighbour(cities, closestCity);
			result.add(cluster);
		}
		return result;
	}

	/*public static ArrayList<Point2D> nearestCluster(ArrayList<ArrayList<Point2D>> clusters)
	{
		//cities = farthestNeighbour(cities);
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		Point2D currentCity = clusters.get(0).remove(0);
		double distance;
		Point2D closestCity = clusters.get(0).get(1);

		while(clusters.size() > 0)
		{
			result.add(currentCity);
			distance = Double.POSITIVE_INFINITY;
			for (ArrayList<Point2D> possible : clusters)
			{
				for(Point2D point : possible)
				{
					if (getDistance(currentCity, possible) < distance) {
						closestCity = possible;
						distance = getDistance(currentCity, possible);
					}
				}
			}
			cities.remove(closestCity);
			currentCity = closestCity;
		}
		return result;
	}*/

	public static double getDistance(Point2D currentCity, Point2D possibleNextCity)
	{
		return currentCity.distance(possibleNextCity);
	}

}
