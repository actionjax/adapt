import random
import csv
import datetime

def generate(name):
	with open(name, 'wb') as csvfile:
		writer = csv.writer(csvfile)
		writer.writerow(['dtoi','mean','low','high'])
		for x in range(0,365):
			d = datetime.datetime(2015, 1, 1, 0, 0, 0)
			d = d + datetime.timedelta(days=x)
			mean = int(round(random.gauss(4,2),0))
			variance = random.randint(0,4)
			mi = mean-variance
			ma = mean+variance
			d.strftime("%Y-%m-%dT%H:%M:%S")
			writer.writerow([d,mean,mi,ma])

generate('data/adapt_observation.csv')
generate('data/adapt_prediction.csv')
