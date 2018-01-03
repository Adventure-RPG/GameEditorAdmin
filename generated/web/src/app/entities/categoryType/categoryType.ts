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

export class CategoryType {
    // Raw attributes
    id : number;
    name : string;
    description : string;
    creationDate : Date;
    creationAuthor : string;
    lastModificationDate : Date;
    lastModificationAuthor : string;
    version : number;
    // x-to-one
    cat : CategoryType;


    constructor(json? : any) {
        if (json != null) {
            this.id = json.id;
            this.name = json.name;
            this.description = json.description;
            if (json.creationDate != null) {
                this.creationDate = new Date(json.creationDate);
            }
            this.creationAuthor = json.creationAuthor;
            if (json.lastModificationDate != null) {
                this.lastModificationDate = new Date(json.lastModificationDate);
            }
            this.lastModificationAuthor = json.lastModificationAuthor;
            this.version = json.version;

            if (json.cat != null) {
                this.cat = new CategoryType(json.cat);
            }
        }
    }

    // Utils

    static toArray(jsons : any[]) : CategoryType[] {
        let categoryTypes : CategoryType[] = [];
        if (jsons != null) {
            for (let json of jsons) {
                categoryTypes.push(new CategoryType(json));
            }
        }
        return categoryTypes;
    }
}