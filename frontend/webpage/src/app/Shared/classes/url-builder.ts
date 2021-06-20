import { QueryStringParameters } from './query-string-parameters';

export class UrlBuilder {
    public url: string;
    public querystring: QueryStringParameters;

    constructor(
        private baseUrl: string,
        private action: string,
        queryString?: QueryStringParameters
    ) {
        this.url = [baseUrl, action].join('/');
        this.querystring = queryString || new QueryStringParameters();
    }

    public toString(): string {
        const qs: string = this.querystring ? this.querystring.toString(): '';

        return qs ? `${this.url}?${qs}` : this.url
    }
}