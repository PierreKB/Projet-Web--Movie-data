import { Component, OnInit } from '@angular/core';
import { Movie } from '../entity/movie'
import { MovieService } from '../service/movie.service';

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {
    titles;



    constructor(private movieService: MovieService) {
    }

    ngOnInit() {
    }
    
    ngAfterViewInit() {
        this.movieService.getMoviesTitlesAndFormat().subscribe(
            res => {
                this.titles = {};

                for(const title of res) {
                    this.titles[title] = null;
                }

                var elems = document.querySelectorAll('.autocomplete');
                var t = this;

                var instances = M.Autocomplete.init(elems, {
                    data: this.titles,
                    onAutocomplete: function(val) {
                        t.movieService.getMovieByTitle(val).subscribe(
                            res => {
                                t.movieService.movie = res;
                                

                                t.movieService.constructBarChart();
                                t.movieService.constructLineChart();
                                t.movieService.setMovieInfo();
                            }
                        );
                    },
                });
            }
        )
    }

}