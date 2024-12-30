import { Component } from '@angular/core';

@Component({
  selector: 'app-home-page',
  imports: [],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {
  private readonly oidcSecurityService = inject(OidcSecurityService);
  private readonly productService = inject(ProductService);
  private readonly orderService = inject(OrderService);
  private readonly router = inject(Router);
  isAuthenticated = false;
  products: Array<Product> = [];
  quantityIsNull = false;
  orderSuccess = false;
  orderFailed = false;

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(
      ({isAuthenticated}) => {
        this.isAuthenticated = isAuthenticated;
        this.productService.getProducts()
          .pipe()
          .subscribe(product => {
            this.products = product;
          })
      }
    )
  }

  goToCreateProductPage() {
    this.router.navigateByUrl('/add-product');
  }

  orderProduct(product: Product, quantity: string) {

    this.oidcSecurityService.userData$.subscribe(result => {
      const userDetails = {
        email: result.userData.email,
        firstName: result.userData.firstName,
        lastName: result.userData.lastName
      };

      if(!quantity) {
        this.orderFailed = true;
        this.orderSuccess = false;
        this.quantityIsNull = true;
      } else {
        const order: Order = {
          skuCode: product.skuCode,
          price: product.price,
          quantity: Number(quantity),
          userDetails: userDetails
        }

        this.orderService.orderProduct(order).subscribe(() => {
          this.orderSuccess = true;
        }, error => {
          this.orderFailed = false;
        })
      }
    })
  }
}