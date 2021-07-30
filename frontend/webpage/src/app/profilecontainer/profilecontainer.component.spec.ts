import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilecontainerComponent } from './profilecontainer.component';

describe('ProfilecontainerComponent', () => {
  let component: ProfilecontainerComponent;
  let fixture: ComponentFixture<ProfilecontainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilecontainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilecontainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
