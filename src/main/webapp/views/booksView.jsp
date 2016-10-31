<!DOCTYPE html>
<section ng-controller="BookController as bookCtrl">

    <div class="container">

        <div class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">Search books</label>
                <input type="text" ng-model="bookCtrl.query" class="form-control" id="exampleInputName2"
                       placeholder="Enter query">
            </div>
            <button class="btn btn-default" ng-click="bookCtrl.find()">Search</button>
        </div>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Authors</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="book in bookCtrl.books">
                    <td>
                        {{(book.volumeInfo.industryIdentifiers)[0].identifier}}
                    </td>
                    <td>{{book.volumeInfo.title}}</td>
                    <td>{{(book.volumeInfo.authors)[0]}}</td>
                    <td><img src="{{book.volumeInfo.imageLinks.smallThumbnail}}"></td>
                    <td>
                        <button type="button" class="btn btn-sm btn-success"
                                ng-click="bookCtrl.add('b',(book.volumeInfo.industryIdentifiers)[0].identifier)">Ajouter
                        </button>
                        <button type="button" class="btn btn-sm btn-info"
                                ng-click="bookCtrl.details((book.volumeInfo.industryIdentifiers)[0].identifier)">Detail
                        </button>
                    </td>
                    <%--<td>SALHET : {{bookCtrl.status}}</td>--%>
                </tr>
                </tbody>
            </table>
        </div>

    </div>

</section>
