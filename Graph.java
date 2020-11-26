//Rose Tabares - CS245 - Assignment02 - Best Road Trip Route - Graph.java class

import java.util.*;
#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <set>
using namespace std;

public int main() {

	ifstream ip("input.csv");

	string v1, v2, distance;

	set < string > used;
	set < string > :: iterator it;
	map < string, bool > inbound;
	map < string, bool > outbound;
	map < string, bool > selfEdges;

	while(ip.good()) {

		getline(ip, v1, ',');
		getline(ip, v2, ',');
		getline(ip, distance, '\n');

		used.insert(v1);
		used.insert(v2);
		inbound[v2] = 1;
		outbound[v1] = 1;

		if (v1 == v2) {
		selfEdges[v1] = 1;
		}

	}

	int totalNumber = used.size();
	vector < string > zeroInbounds;
	vector < string > zeroOutbounds;
	vector < string > loopVertices;

	for (it = used.begin(); it != used.end(); it++) {
		if (!inbound[*it]) {
		zeroInbounds.push_back(*it);
		}
		if (!outbound[*it]) {
		zeroOutbounds.push_back(*it);
		}
		if (selfEdges[*it]) {
		loopVertices.push_back(*it);
		}
	}

	cout << "Total Number : " << totalNumber << endl;

	cout << "Vertices with zero inbound edges : " << endl;
	
	for (int i = 0; i < zeroInbounds.size(); i++) {
		cout << zeroInbounds[i] << " ";
	}
	cout << endl;

	cout << "Vertices with self edges : " << endl;
	for (int i = 0; i < loopVertices.size(); i++) {
		cout << loopVertices[i] << " ";
	}
	cout << endl;

	cout << "Vertices with zero outbound edges : " << endl;
	for (int i = 0; i < zeroOutbounds.size(); i++) {
		cout << zeroOutbounds[i] << " ";
	}

	return 0;
}



public class Graph{





}
