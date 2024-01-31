import React, { useEffect, useState } from "react";
import productService from "../servie/product.service";
import { Link } from "react-router-dom";

function Home() {
  const [productList, setProductList] = useState([]);
  const [message, setMessage] = useState();

  useEffect(() => {
    initProductList();
  }, []);

  const initProductList = () => {
    productService
      .getAllProduct()
      .then((response) => {
        setProductList(response.data);
      })
      .catch((error) => {
        console.log("====================================");
        console.log(error);
        console.log("====================================");
      });
  };

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      setMessage("");
    }, 3000);

    return () => clearTimeout(timeoutId);
  }, [message]);

  const deleteProduct = (id) => {
    productService
      .deleteProduct(id)
      .then((response) => {
        setMessage("Product deleted Successfully!");
        initProductList();
      })
      .catch((error) => {
        console.log("====================================");
        console.log(error);
        console.log("====================================");
      });
  };

  return (
    <>
      <div className="container mt-3">
        <div className="row">
          <div className="col-md-15">
            <div className="card">
              <Link
                className="btn btn-primary nav-link active"
                to="/add"
                aria-current="page"
                href="#"
              >
                Add Product
              </Link>
              <div className="card-header fs-3 text-center">
                All Product
                {message && (
                  <p className="fs-4 text-center text-success">{message}</p>
                )}
              </div>
              <div className="card-body">
                <table className="table">
                  <thead>
                    <tr>
                      <th scope="col">Id.</th>
                      <th scope="col">Product Name</th>
                      <th scope="col">Description</th>
                      <th scope="col">Price</th>
                      <th scope="col">Status</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {productList.map((p, num) => (
                      <tr key={p.id}>
                        <td>{num + 1}</td>
                        <td>{p.productName}</td>
                        <td>{p.description}</td>
                        <td>{p.price}</td>
                        <td>{p.status}</td>
                        <td>
                          <Link
                            to={`/edit/${p.id}`}
                            className="btn btn-sm btn-primary"
                          >
                            Edit
                          </Link>
                          <button
                            onClick={() => deleteProduct(p.id)}
                            className="btn btn-sm btn-danger ms-1"
                          >
                            Delete
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Home;
