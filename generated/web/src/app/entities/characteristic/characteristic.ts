//
// Project home: https://github.com/jaxio/celerio-angular-quickstart
// 
// Source code generated by Celerio, an Open Source code generator by Jaxio.
// Documentation: http://www.jaxio.com/documentation/celerio/
// Source code: https://github.com/jaxio/celerio/
// Follow us on twitter: @jaxiosoft
// This header can be customized in Celerio conf...
// Template pack-angular:web/src/app/entities/entity.ts.e.vm
//
import {Character} from '../character/character';

export class Characteristic {
    // Raw attributes
    id : number;
    strength : number;
    agility : number;
    endurance : number;
    intelligence : number;
    charisma : number;
    perception : number;
    luck : number;
    pointsAvailable : number;
    skillAvailable : number;
    creationDate : Date;
    creationAuthor : string;
    lastModificationDate : Date;
    lastModificationAuthor : string;
    version : number;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.strength = json.strength;
            this.agility = json.agility;
            this.endurance = json.endurance;
            this.intelligence = json.intelligence;
            this.charisma = json.charisma;
            this.perception = json.perception;
            this.luck = json.luck;
            this.pointsAvailable = json.pointsAvailable;
            this.skillAvailable = json.skillAvailable;
            if (json.creationDate != null) {
                this.creationDate = new Date(json.creationDate);
            }
            this.creationAuthor = json.creationAuthor;
            if (json.lastModificationDate != null) {
                this.lastModificationDate = new Date(json.lastModificationDate);
            }
            this.lastModificationAuthor = json.lastModificationAuthor;
            this.version = json.version;
        }
    }

    // Utils

    static toArray(jsons : any[]) : Characteristic[] {
        let characteristics : Characteristic[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                characteristics.push(new Characteristic(json));
            }
        }
        return characteristics;
    }
}