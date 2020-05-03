import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { map } from 'rxjs/operators';
import { Movie } from '../entity/movie';
import { ViewChild, ElementRef } from '@angular/core';
import { Chart } from 'chart.js'

@Injectable({

    providedIn: 'root'

})
export class MovieService {
    movie: Movie;

    constructor(private http: HttpClient) { }

    getMovieByTitle(title): Observable<any> {
        return this.http.get("http://localhost:8082//movie/getByTitle/" + title)
            .pipe(map((response: Response) => response));
    }

    getMoviesTitlesAndFormat(): Observable<any> {
        return this.http.get("http://localhost:8082/movie/titles")
            .pipe(map((response: Response) => response));

    }

    getMovieStatistics(id): Observable<any> {
        return this.http.get("http://localhost:8084/request/rating/statistics/" + id)
            .pipe(map((response: Response) => response));
    }

    getMovieRatingByYear(id): Observable<any> {
        return this.http.get("http://localhost:8084/request/rating/avgByYear/" + id)
            .pipe(map((response: Response) => response));
    }

    getMovieAvgRating(id):  Observable<any> {
        return this.http.get("http://localhost:8084/request/rating/avg/" + id)
            .pipe(map((response: Response) => response));
    }

    getGenre(id):  Observable<any> {
        return this.http.get("http://localhost:8084/request/genre/" + id)
            .pipe(map((response: Response) => response));
    }

    getActors(id):  Observable<any> {
        return this.http.get("http://localhost:8084/request/actors/" + id)
            .pipe(map((response: Response) => response));
    }

    getDirectors(id):  Observable<any> {
        return this.http.get("http://localhost:8084/request/directors/" + id)
            .pipe(map((response: Response) => response));
    }

    getWriters(id):  Observable<any> {
        return this.http.get("http://localhost:8084/request/writers/" + id)
            .pipe(map((response: Response) => response));
    }
    
    getShortPlot(id):  Observable<any> {
        return this.http.get("http://localhost:8084/request/shortplot/" + id)
            .pipe(map((response: Response) => response));
    }

    getNumberOfRating(id):  Observable<any> {
        return this.http.get("http://localhost:8082/movie/rating/total/" + id)
            .pipe(map((response: Response) => response));
    }


    constructBarChart() {
        this.getMovieStatistics(this.movie.id).subscribe(res => {
            var data = [];

            data.push(res["0.5"]);
            data.push(res["1.0"]);
            data.push(res["1.5"]);
            data.push(res["2.0"]);
            data.push(res["2.5"]);
            data.push(res["3.0"]);
            data.push(res["3.5"]);
            data.push(res["4.0"]);
            data.push(res["4.5"]);
            data.push(res["5.0"]);

            var element = document.createElement("canvas");
            element.id = "bar-chart";
            element.setAttribute("style", "height: 100%;width: 80%")
            var context = element.getContext('2d');
            document.getElementById('chart-div').innerHTML = '';
            document.getElementById('chart-div').appendChild(element);

           
            var chart = new Chart(context, {
                type: 'bar',

                data: {
                    labels: ['0.5', '1.0', '1.5', '2.0', '2.5', '3.0', '3.5', '4.0', '4.5', '5.0'],
                    datasets: [{
                        label: 'Number of rating by increment',
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: data
                    }]
                },
                
                options: {}

            });

        });
    }

    constructLineChart() {
        this.getMovieRatingByYear(this.movie.id).subscribe(res => {

            var labels = [];
            var data = [];

            for(var key in res) {
                labels.push(key);
                data.push(res[key]);
            }

            var element = document.createElement("canvas");
            element.id = "line-chart";
            element.setAttribute("style", "height: 100%;width: 80%")
            var context = element.getContext('2d');
            document.getElementById('line-char-div').innerHTML = '';
            document.getElementById('line-char-div').appendChild(element);

           
            var chart = new Chart(context, {
                type: 'line',

                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Average rating by year',
                        backgroundColor: 'rgba(146, 212, 219)',
                        borderColor: 'rgba(146, 212, 219)',
                        data: data,
                        fill: false
                    }]
                },

                options: {
					responsive: true,
					tooltips: {
						mode: 'index',
						intersect: false,
					},
					hover: {
						mode: 'nearest',
						intersect: true
					},
					scales: {
						x: {
							display: true,
							scaleLabel: {
								display: true,
								labelString: 'Year'
							}
						},
						y: {
							display: true,
							scaleLabel: {
								display: true,
								labelString: 'Rating'
							}
						}
					}
				}

            });
        });
    }

    setMovieInfo() {
        document.getElementById('title').innerText = this.movie.title;

        this.getMovieAvgRating(this.movie.id).subscribe(res => {
            document.getElementById('avgRating').innerText = res.toFixed(2) + ' / 5.0';
        });

        this.getNumberOfRating(this.movie.id).subscribe(res => {
            document.getElementById('numberOfRating').innerText = res + ' ratings';
        });

        this.getGenre(this.movie.id).subscribe(res => {
            document.getElementById('genre').innerHTML = '<span style="color: rgb(134, 159, 28);">Genre: </span> <span >' + res["genre"].replace(",", ", ") + '</span>';
        });

        this.getActors(this.movie.id).subscribe(res => {
            document.getElementById('actors').innerHTML = '<span style="color: rgb(134, 159, 28);">Actors: </span> <span >' + res.toString().replace(",", ", ") + '</span>';
        });

        this.getDirectors(this.movie.id).subscribe(res => {
            document.getElementById('directors').innerHTML = '<span style="color: rgb(134, 159, 28);">Directors: </span> <span >' + res.toString().replace(",", ", ") + '</span>';
        });

        this.getWriters(this.movie.id).subscribe(res => {
            document.getElementById('writers').innerHTML = '<span style="color: rgb(134, 159, 28);">Writers: </span> <span >' + res.toString().replace(",", ", ") + '</span>';
        });

        this.getShortPlot(this.movie.id).subscribe(res => {
            document.getElementById('plot').innerHTML = res;
        });
    }

}