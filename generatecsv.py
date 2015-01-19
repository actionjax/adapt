import random
import csv
import datetime

def generate(name):
	with open(name, 'wb') as csvfile:
		writer = csv.writer(csvfile)
		writer.writerow(['dtoi','mean','low','high','obslow','obshigh'])
		random_walk = 5
		for x in range(0,365):
			d = datetime.datetime(2015, 1, 1, 0, 0, 0)
			d = d + datetime.timedelta(days=x)


			mean = int(round(random.gauss(5,4),0))
			variance = random.randint(0,4)
			omi = mean-variance
			oma = mean+variance

			walk = int(round(random.gauss(0,4),0))
			walk = random_walk + walk
			if walk < 0:
				walk = int(round(random.gauss(4,4),0))
				walk = random_walk + walk
			if walk > 10:
				walk = int(round(random.gauss(-4,4),0))
				walk = random_walk + walk

			random_walk = walk
			variance = random.randint(0,4)
			mi = mean-variance
			ma = mean+variance
			d.strftime("%Y-%m-%dT%H:%M:%S")
			writer.writerow([d,mean,mi,ma,omi,oma])

generate('data/adapt_prediction.csv')
