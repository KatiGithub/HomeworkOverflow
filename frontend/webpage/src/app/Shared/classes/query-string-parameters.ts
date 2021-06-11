export class QueryStringParameters {
    private paramsAndValues: string[];

    constructor() {
        this.paramsAndValues = [];
    }

    public push(key:string, value: string) {
        value = encodeURIComponent(value);
        this.paramsAndValues.push([key, value].join('='));
    }

    public toString() {
        return this.paramsAndValues.join('&');
    }
}