//
// Project home: https://github.com/jaxio/celerio-angular-quickstart
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Source code: https://github.com/jaxio/celerio/
// Follow us on twitter: @jaxiosoft
// This header can be customized in Celerio conf...
// Template pack-angular:web/src/app/entities/entity.service.ts.e.vm
//
import { Injectable } from '@angular/core';
import { HttpModule, Http, Response, Headers, RequestOptions } from '@angular/http';
import { LazyLoadEvent } from 'primeng/primeng';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { MessageService } from '../../service/message.service';
import { PageResponse, PageRequestByExample } from '../../support/paging';
import { RaceOption } from './raceOption';

@Injectable()
export class RaceOptionService {

    private options = new RequestOptions({ headers: new Headers({ 'Content-Type': 'application/json' }) });

    constructor(private http: Http, private messageService : MessageService) {}

    /**
     * Get a RaceOption by id.
     */
    getRaceOption(id : any) : Observable<RaceOption> {
        return this.http.get('/api/raceOptions/' + id)
            .map(response => new RaceOption(response.json()))
            .catch(this.handleError);
    }

    /**
     * Update the passed raceOption.
     */
    update(raceOption : RaceOption) : Observable<RaceOption> {
        let body = JSON.stringify(raceOption);

        return this.http.put('/api/raceOptions/', body, this.options)
            .map(response => new RaceOption(response.json()))
            .catch(this.handleError);
    }

    /**
     * Load a page (for paginated datatable) of RaceOption using the passed
     * raceOption as an example for the search by example facility.
     */
    getPage(raceOption : RaceOption, event : LazyLoadEvent) : Observable<PageResponse<RaceOption>> {
        let req = new PageRequestByExample(raceOption, event);
        let body = JSON.stringify(req);

        return this.http.post('/api/raceOptions/page', body, this.options)
            .map(response => {
                let pr : any = response.json();
                return new PageResponse<RaceOption>(pr.totalPages, pr.totalElements, RaceOption.toArray(pr.content));
            })
            .catch(this.handleError);
    }

    /**
     * Performs a search by example on 1 attribute (defined on server side) and returns at most 10 results.
     * Used by RaceOptionCompleteComponent.
     */
    complete(query : string) : Observable<RaceOption[]> {
        let body = JSON.stringify({'query': query, 'maxResults': 10});
        return this.http.post('/api/raceOptions/complete', body, this.options)
            .map(response => RaceOption.toArray(response.json()))
            .catch(this.handleError);
    }

    /**
     * Delete an RaceOption by id.
     */
    delete(id : any) {
        return this.http.delete('/api/raceOptions/' + id).catch(this.handleError);
    }

    // sample method from angular doc
    private handleError (error: any) {
        // TODO: seems we cannot use messageService from here...
        let errMsg = (error.message) ? error.message :
        error.status ? `Status: ${error.status} - Text: ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        if (error.status === 401 ) {
            window.location.href = '/';
        }
        return Observable.throw(errMsg);
    }
}
