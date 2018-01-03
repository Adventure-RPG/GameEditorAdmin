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
import {Script} from '../script/script';
import {Character} from '../character/character';

export class Skill {
    // Raw attributes
    id : number;
    name : string;
    position : boolean;
    onInit : boolean;
    creationDate : Date;
    creationAuthor : string;
    lastModificationDate : Date;
    lastModificationAuthor : string;
    version : number;
    // x-to-one
    skill2 : Skill;
    script : Script;
    // many-to-many
    theChars : Character[];


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.name = json.name;
            this.position = json.position;
            this.onInit = json.onInit;
            if (json.creationDate != null) {
                this.creationDate = new Date(json.creationDate);
            }
            this.creationAuthor = json.creationAuthor;
            if (json.lastModificationDate != null) {
                this.lastModificationDate = new Date(json.lastModificationDate);
            }
            this.lastModificationAuthor = json.lastModificationAuthor;
            this.version = json.version;

            if (json.skill2 != null) {
                this.skill2 = new Skill(json.skill2);
            }

            if (json.script != null) {
                this.script = new Script(json.script);
            }

            if (json.theChars != null) {
                this.theChars = Character.toArray(json.theChars);
            }
        }
    }

    // Utils

    static toArray(jsons : any[]) : Skill[] {
        let skills : Skill[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                skills.push(new Skill(json));
            }
        }
        return skills;
    }
}