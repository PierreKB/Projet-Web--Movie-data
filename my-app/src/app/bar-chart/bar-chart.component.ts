import { Component, OnInit } from '@angular/core';
import { Movie } from '../entity/movie'
import { MovieService } from '../service/movie.service';

@Component({
    selector: 'app-bar-chart',
    templateUrl: './bar-chart.component.html',
    styleUrls: ['./bar-chart.component.css']
})

export class BarChartComponent implements OnInit {
    titles;

    constructor(private movieService: MovieService) {
    }
    
    ngOnInit() {
    }

    ngAfterViewInit() {
        
    }

}