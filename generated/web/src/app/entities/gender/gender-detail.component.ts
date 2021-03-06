//
// Project home: https://github.com/jaxio/celerio-angular-quickstart
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Source code: https://github.com/jaxio/celerio/
// Follow us on twitter: @jaxiosoft
// This header can be customized in Celerio conf...
// Template pack-angular:web/src/app/entities/entity-detail.component.ts.e.vm
//
import {Component, OnInit, OnDestroy, Input, Output, EventEmitter} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SelectItem } from 'primeng/primeng';
import { MessageService} from '../../service/message.service';
import {Gender} from './gender';
import {GenderService} from './gender.service';
import {RaceOption} from '../raceOption/raceOption';
import {RaceOptionService} from '../raceOption/raceOption.service';

@Component({
    moduleId: module.id,
	templateUrl: 'gender-detail.component.html',
	selector: 'gender-detail',
})
export class GenderDetailComponent implements OnInit, OnDestroy {
    gender : Gender;

    private params_subscription: any;

    sourceTheRacesOptions : RaceOption[] = [];

    @Input() sub : boolean = false;
    @Output() onSaveClicked = new EventEmitter<Gender>();
    @Output() onCancelClicked = new EventEmitter();

    constructor(private route: ActivatedRoute, private router: Router, private messageService: MessageService, private genderService: GenderService, private raceOptionService : RaceOptionService) {
        raceOptionService.complete(null).
            subscribe(theRacesOptions => this.sourceTheRacesOptions = theRacesOptions,
                        error =>  this.messageService.error('Constructor error', error));
    }

    ngOnInit() {
        if (this.sub) {
            return;
        }

        this.params_subscription = this.route.params.subscribe(params => {
            let id = params['id'];
            console.log('ngOnInit for gender-detail ' + id);

            if (id === 'new') {
                this.gender = new Gender();
            } else {
                this.genderService.getGender(id)
                    .subscribe(gender => {
                            this.gender = gender;
                            this.sourceTheRacesOptions = this.sourceTheRacesOptions.filter(item => this.gender.theRacesOptions.map((e) => e.id).indexOf(item.id) < 0);
                        },
                        error =>  this.messageService.error('ngOnInit error', error)
                    );
            }
        });
    }

    ngOnDestroy() {
        if (!this.sub) {
            this.params_subscription.unsubscribe();
        }
    }

    onSave() {
        this.genderService.update(this.gender).
            subscribe(
                gender => {
                    this.gender = gender;
                    if (this.sub) {
                        this.onSaveClicked.emit(this.gender);
                        this.messageService.info('Saved OK and msg emitted', 'Angular Rocks!')
                    } else {
                        this.messageService.info('Saved OK', 'Angular Rocks!')
                    }
                },
                error =>  this.messageService.error('Could not save', error)
            );
    }

    onCancel() {
        if (this.sub) {
            this.onCancelClicked.emit("cancel");
            this.messageService.info('Cancel clicked and msg emitted', 'Angular Rocks!')
        }
    }

}
