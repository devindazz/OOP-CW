import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import axios, { AxiosInstance } from "axios";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private instance: AxiosInstance;

  constructor() {
    this.instance = axios.create({
      baseURL: "http://localhost:8080"
    });
  }

  get client() {
    return this.instance;
  }

}

