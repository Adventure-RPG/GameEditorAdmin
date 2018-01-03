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
import {RaceOption} from './raceOption';
import {RaceOptionService} from './raceOption.service';
import {Gender} from '../gender/gender';
import {GenderService} from '../gender/gender.service';
import {Model} from '../model/model';
import {ModelService} from '../model/model.service';

@Component({
    moduleId: module.id,
	templateUrl: 'raceOption-detail.component.html',
	selector: 'raceOption-detail',
})
export class RaceOptionDetailComponent implements OnInit, OnDestroy {
    raceOption : RaceOption;

    private params_subscription: any;

    sourceTheGenders : Gender[] = [];
    sourceTheModels : Model[] = [];

    @Input() sub : boolean = false;
    @Output() onSaveClicked = new EventEmitter<RaceOption>();
    @Output() onCancelClicked = new EventEmitter();

    constructor(private route: ActivatedRoute, private router: Router, private messageService: MessageService, private raceOptionService: RaceOptionService, private genderService : GenderService, private modelService : ModelService) {
        genderService.complete(null).
            subscribe(theGenders => this.sourceTheGenders = theGenders,
                        error =>  this.messageService.error('Constructor error', error));
        modelService.complete(null).
            subscribe(theModels => this.sourceTheModels = theModels,
                        error =>  this.messageService.error('Constructor error', error));
    }

    ngOnInit() {
        if (this.sub) {
            return;
        }

        this.params_subscription = this.route.params.subscribe(params => {
            let id = params['id'];
            console.log('ngOnInit for raceOption-detail ' + id);

            if (id === 'new') {
                this.raceOption = new RaceOption();
            } else {
                this.raceOptionService.getRaceOption(id)
                    .subscribe(raceOption => {
                            this.raceOption = raceOption;
                            this.sourceTheGenders = this.sourceTheGenders.filter(item => this.raceOption.theGenders.map((e) => e.id).indexOf(item.id) < 0);
                            this.sourceTheModels = this.sourceTheModels.filter(item => this.raceOption.theModels.map((e) => e.id).indexOf(item.id) < 0);
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
        this.raceOptionService.update(this.raceOption).
            subscribe(
                raceOption => {
                    this.raceOption = raceOption;
                    if (this.sub) {
                        this.onSaveClicked.emit(this.raceOption);
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